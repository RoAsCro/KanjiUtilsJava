package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.dao.mappers.WordMapper;
import self.roashe.kanutils.backend.dto.Word;

import java.util.*;

@Repository
public class VocabDaoDbImpl implements VocabDao {

    @Autowired
    private JdbcTemplate jdbc;

    private final Set<Word> wordSet = new HashSet<>();

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
            Word word = this.jdbc.queryForObject(SELECT_WORD, new WordMapper(), jpWord);
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

    public void clearLocalData() {
        this.wordSet.clear();
    }

    private void addDefinitions(Word word) {
        addList(word, word.getEnglish(), "definition");
    }

    private void addReadings(Word word) {
        addList(word, word.getReadings(), "reading");

    }

    private void addTags(Word word) {
        addList(word, word.getTags(), "tag");
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
        final String SELECT_READINGS = "SELECT reading FROM reading " +
                "WHERE japaneseword_jpId = ?";
        List<String> readings = this.jdbc.queryForList(SELECT_READINGS, String.class, word.getId());
        word.setReadings(readings);
    }

    private void pullEnglish(Word word) {
        final String SELECT_DEFINITIONS = "SELECT definition FROM definition " +
                "WHERE japaneseword_jpId = ?";
        List<String> definitions = this.jdbc.queryForList(SELECT_DEFINITIONS, String.class, word.getId());
        word.setEnglish(definitions);
    }

    private void pullTags(Word word) {
        final String SELECT_TAGS = "SELECT tag FROM tag " +
                "WHERE japaneseword_jpId = ?";

        List<String> tags = this.jdbc.queryForList(SELECT_TAGS, String.class, word.getId());
        word.setTags(tags);
    }

}
