package self.roashe.kanutils.backend.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import self.roashe.kanutils.backend.TestApplicationConfiguration;
import self.roashe.kanutils.backend.model.Word;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class VocabDaoDbImplTest {
    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    VocabDao dao;


    @BeforeEach
    void setUp() {
        jdbc.update("DELETE FROM definition");
        jdbc.update("DELETE FROM reading");
        jdbc.update("DELETE FROM japaneseword");

        jdbc.update("INSERT INTO japaneseword(word) " +
                        "values('水')");
        Integer wordId =
                jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        jdbc.update("INSERT INTO definition(japaneseword_jpId, definition) " +
                "values(?, 'water')",
                wordId);
        jdbc.update("INSERT INTO reading(japaneseword_jpId, reading) " +
                        "values(?, 'みず')",
                wordId);


        jdbc.update("INSERT INTO japaneseword(word) " +
                "values('叫ぶ')");
        wordId =
                jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        jdbc.update("INSERT INTO definition(japaneseword_jpId, definition) " +
                        "values(?, 'cry out')",
                wordId);
        jdbc.update("INSERT INTO reading(japaneseword_jpId, reading) " +
                        "values(?, 'さけぶ')",
                wordId);
    }

    @Test
    void testConfiguration(){
        Assertions.assertNotNull(dao);
    }

    @Test
    void testGetAll() {
        List<Word> wordList = dao.getWords();
        Assertions.assertTrue(wordList
                .stream()
                .anyMatch(d -> d.getJapanese().equals("水")));
        Assertions.assertEquals(2, wordList.size());
    }

    @Test
    void testGet() {
        Word word = dao.getWord("水");
        Assertions.assertNotNull(word);
        Assertions.assertEquals("水", word.getJapanese());
        Assertions.assertEquals(List.of("みず"), word.getReadings());
        Assertions.assertEquals(List.of("water"), word.getEnglish());
    }

    @Test
    void testGetDoesNotExist() {
        Word word = dao.getWord("海");
        Assertions.assertNull(word);
    }

    @Test
    void testAdd() {
        Word word = new Word();
        word.setJapanese("是非");
        word.setReadings(List.of("ぜひ"));
        word.setEnglish(List.of("Very much", "Right and wrong"));
        dao.addWord(word);
        Word retrievedWord = dao.getWord("是非");
        Assertions.assertNotNull(retrievedWord);
        Assertions.assertEquals(word, retrievedWord);
    }

}