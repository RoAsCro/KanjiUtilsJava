package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.dao.mappers.WordMapper;
import self.roashe.kanutils.backend.model.Word;

import java.util.List;

@Repository
public class VocabDaoDbImpl implements VocabDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Word> getWords() {
        final String SELECT_ALL_WORDS = "SELECT * FROM japaneseword";
        List<Word> words = this.jdbc.query(SELECT_ALL_WORDS, new WordMapper());

        for (Word word : words) {
            addReadings(word);
            addEnglish(word);
        }

        return words;
    }

    @Override
    public Word getWord(String jpWord) {
        final String SELECT_WORD = "SELECT * FROM japaneseword " +
                "WHERE word = ?";

        try {
            Word word = this.jdbc.queryForObject(SELECT_WORD, new WordMapper(), jpWord);
            addReadings(word);
            addEnglish(word);
            return word;
        } catch (DataAccessException e) {
            return null;
        }
    }


    @Override
    public void addWord(Word word) {
    }

    private void addReadings(Word word) {
        final String SELECT_READINGS = "SELECT reading FROM reading " +
                "WHERE japaneseword_jpId = ?";
        List<String> readings = this.jdbc.queryForList(SELECT_READINGS, String.class, word.getId());
        word.setReadings(readings);
    }

    private void addEnglish(Word word) {
        final String SELECT_DEFINITIONS = "SELECT definition FROM definition " +
                "WHERE japaneseword_jpId = ?";
        List<String> definitions = this.jdbc.queryForList(SELECT_DEFINITIONS, String.class, word.getId());
        word.setEnglish(definitions);
    }

}
