package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.dao.mappers.WordMapper;
import self.roashe.kanutils.backend.dto.Word;

import java.util.*;
import java.util.function.Consumer;

@Repository
public class VocabDaoDbImpl implements VocabDao {

    @Autowired
    private JdbcTemplate jdbc;

    private final Set<Word> wordSet = new HashSet<>();

    private static final String DEFINITION_TABLE = "definition";
    private static final String READING_TABLE = "reading";
    private static final String TAG_TABLE = "tag";

    @Override
    public List<Word> getWords() {
        if (this.wordSet.isEmpty()) {
            final String SELECT_ALL_WORDS = "SELECT * FROM japaneseword";
            List<Word> words = this.jdbc.query(SELECT_ALL_WORDS, new WordMapper());

            for (Word word : words) {
                pullAll(word);
            }
            this.wordSet.addAll(words);
        }

        return List.copyOf(this.wordSet);
    }

    @Override
    public Word getWord(String jpWord) {
        final String SELECT_WORD = "SELECT * FROM japaneseword " +
                "WHERE word = ?";

        try {
            // TODO: this can easily pull multiple words
            Word word = this.jdbc.queryForObject(SELECT_WORD, new WordMapper(), jpWord);
            pullAll(word);
            return word;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Word getWordById(int id) {
        final String SELECT_WORD = "SELECT * FROM japaneseword " +
                "WHERE jpId = ?";

        try {
            Word word = this.jdbc.queryForObject(SELECT_WORD, new WordMapper(), id);
            pullAll(word);
            return word;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void addWord(Word word) {
        getWords();
        if (!this.wordSet.add(word)) {
            return;
        }
        final String ADD_WORD = "INSERT INTO japaneseword(word) " +
                "values(?)";
        this.jdbc.update(ADD_WORD,
                word.getJapanese());
        int id = this.jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        word.setId(id);
        addReadings(word);
        addDefinitions(word);
        addTags(word);
    }

    @Override
    public void updateWord(Word word) {
        deleteWord(word.getId());
        addWord(word);
        if (!this.wordSet.isEmpty()) {
            Word existingWord = this.wordSet.stream()
                    .filter(w -> w.getId() == word.getId())
                    .findAny()
                    .orElse(null);
            this.wordSet.remove(existingWord);
            this.wordSet.add(word);
        }
    }

    @Override
    public void deleteWord(int id) {
        Word word = getWordById(id);
        final String DELETE_TAGS = "DELETE FROM tag WHERE japaneseword_jpId = ?";
        this.jdbc.update(DELETE_TAGS, id);
        final String DELETE_KANJI_ASSOCIATION = "DELETE FROM word_has_kanji WHERE japaneseword_jpId = ?";
        this.jdbc.update(DELETE_KANJI_ASSOCIATION, id);
        final String DELETE_DEFINITION = "DELETE FROM definition WHERE japaneseword_jpId = ?";
        this.jdbc.update(DELETE_DEFINITION, id);
        final String DELETE_READING = "DELETE FROM reading WHERE japaneseword_jpId = ?";
        this.jdbc.update(DELETE_READING, id);
        final String DELETE_JAPANESE = "DELETE FROM japaneseword WHERE jpId = ?";
        this.jdbc.update(DELETE_JAPANESE, id);
        this.wordSet.remove(word);
    }

    public void clearLocalData() {
        this.wordSet.clear();
    }

    private void addDefinitions(Word word) {
        addList(word, word.getEnglish(), DEFINITION_TABLE);
    }

    private void addReadings(Word word) {
        addList(word, word.getReadings(), READING_TABLE);

    }

    private void addTags(Word word) {
        addList(word, word.getTags(), TAG_TABLE);
    }

    private void addList(Word word, List<String> toAdd, String table) {
        if (toAdd == null) {
            return;
        }
        String ADD_ITEM = "INSERT INTO " +
            table + "(" + table + ", japaneseword_jpId) " +
                    "values(?, ?)";
        int id = word.getId();
        for (String s : toAdd) {
            this.jdbc.update(ADD_ITEM,
                    s,
                    id);
        }
    }

    private void pullAll(Word word) {
        pullReadings(word);
        pullEnglish(word);
        pullTags(word);
    }

    private void pullReadings(Word word) {
        pullList(word, READING_TABLE, word::setReadings);

    }

    private void pullEnglish(Word word) {
        pullList(word, DEFINITION_TABLE, word::setEnglish);
    }

    private void pullTags(Word word) {
        pullList(word, TAG_TABLE, word::setTags);

    }

    private void pullList(Word word, String table, Consumer<List<String>> setMethod) {
        String SELECT_ITEM = "SELECT " + table + " FROM " + table + " " +
                "WHERE japaneseword_jpId = ?";

        List<String> tags = this.jdbc.queryForList(SELECT_ITEM, String.class, word.getId());
        setMethod.accept(tags);
    }

}
