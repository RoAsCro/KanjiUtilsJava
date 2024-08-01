package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
        final String SELECT_ALL_WORDS = "SELECT * FROM japanese-word";
        List<Word> words = this.jdbc.query(SELECT_ALL_WORDS, new WordMapper());
        return words;
    }

    @Override
    public Word getWord(String jpWord) {
        return null;
    }

    @Override
    public void addWord(Word word) {
    }

//    private void add
}
