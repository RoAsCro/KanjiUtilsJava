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
import self.roashe.kanutils.backend.dto.Word;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class VocabDaoDbImplTest {
    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    VocabDaoDbImpl dao;


    @BeforeEach
    void setUp() {
        jdbc.update("DELETE FROM definition");
        jdbc.update("DELETE FROM reading");
        jdbc.update("DELETE FROM tag");
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
        jdbc.update("INSERT INTO tag(tag, japaneseword_jpId) " +
                "values('test', ?)",
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
        dao.clearLocalData();
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
        Assertions.assertEquals(List.of("test"), word.getTags());
    }

    @Test
    void testGetNoTags(){
        Word word = dao.getWord("叫ぶ");
        Assertions.assertNotNull(word);
        Assertions.assertEquals(List.of(), word.getTags());
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
        dao.clearLocalData();
        Word retrievedWord = dao.getWord("是非");
        Assertions.assertNotNull(retrievedWord);
        Assertions.assertEquals(word, retrievedWord);
    }

    @Test
    void testAddWithTags() {
        Word word = new Word();
        word.setJapanese("是非");
        word.setReadings(List.of("ぜひ"));
        word.setEnglish(List.of("Very much", "Right and wrong"));
        List<String> tags = List.of("test1", "test2");
        word.setTags(tags);
        dao.addWord(word);
        dao.clearLocalData();
        Word retrievedWord = dao.getWord("是非");
        Assertions.assertNotNull(retrievedWord);
        Assertions.assertEquals(tags, retrievedWord.getTags());
    }

    @Test
    void testAddExists(){
        Word word = new Word();
        word.setJapanese("是非");
        word.setReadings(List.of("ぜひ"));
        word.setEnglish(List.of("Very much", "Right and wrong"));
        System.out.println(word.hashCode());
        dao.addWord(word);
        word = new Word();
        word.setJapanese("是非");
        word.setReadings(List.of("ぜひ"));
        word.setEnglish(List.of("Right and wrong", "Very much"));
        List<String> tags = List.of("test1", "test2");
        word.setTags(tags);
        dao.addWord(word);

        Word retrievedWord = dao.getWord("是非");
        Assertions.assertEquals(List.of(), retrievedWord.getTags());
    }

    @Test
    void testDelete(){
        Word word = dao.getWord("水");
        Assertions.assertEquals(2, dao.getWords().size());
        dao.deleteWord(word.getId());
        Assertions.assertEquals(1, dao.getWords().size());
        dao.clearLocalData();
        Assertions.assertEquals(1, dao.getWords().size());
        Assertions.assertNull(dao.getWord("水"));
    }

}