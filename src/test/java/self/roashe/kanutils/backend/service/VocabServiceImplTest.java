package self.roashe.kanutils.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import self.roashe.kanutils.backend.TestApplicationConfiguration;
import self.roashe.kanutils.backend.dao.VocabDao;
import self.roashe.kanutils.backend.dto.Word;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class VocabServiceImplTest {

    @Autowired
    VocabService service;
    @Autowired
    VocabDao dao;
    @Autowired
    JdbcTemplate jdbc;

    private Word word;

    @BeforeEach
    void setUp() {
        jdbc.update("DELETE FROM definition");
        jdbc.update("DELETE FROM reading");
        jdbc.update("DELETE FROM tag");
        jdbc.update("DELETE FROM japaneseword");
    }

    private void setupTagTest() {
        word = new Word();
        word.setJapanese("是非");
        word.setReadings(List.of("ぜひ"));
        word.setEnglish(List.of("Very much", "Right and wrong"));
        List<String> tags = List.of("test1", "test2");
        word.setTags(tags);
        dao.addWord(word);
        Word word2 = new Word();
        word2.setJapanese("水");
        word2.setReadings(List.of("みず"));
        word2.setEnglish(List.of("water"));
        word2.setTags(List.of("test4"));
        dao.addWord(word2);
    }

    @Test
    void testGetByTags() {
        setupTagTest();

        List<Word> retrievedWords = service.getByTags(List.of("test1"));
        Assertions.assertEquals(2, service.getAllVocab().size());
        Assertions.assertEquals(1, retrievedWords.size());
        Assertions.assertEquals(List.of(word), retrievedWords);
    }

    @Test
    void testGetByTagsMultipleOneNotPresent() {
        setupTagTest();
        List<Word> retrievedWords = service.getByTags(List.of("test1", "test3"));
        Assertions.assertEquals(2, service.getAllVocab().size());
        Assertions.assertEquals(1, retrievedWords.size());
        Assertions.assertEquals(List.of(word), retrievedWords);
    }

    @Test
    void testGetByTagsNotPresent() {
        setupTagTest();
        List<Word> retrievedWords = service.getByTags(List.of("test3"));
        Assertions.assertEquals(2, service.getAllVocab().size());
        Assertions.assertEquals(0, retrievedWords.size());
    }

    @Test
    void testGetByTagsMultipleMultiplePresentInDifferent() {
        setupTagTest();
        List<Word> retrievedWords = service.getByTags(List.of("test2", "test4"));
        Assertions.assertEquals(2, service.getAllVocab().size());
        Assertions.assertEquals(2, retrievedWords.size());
    }
}
