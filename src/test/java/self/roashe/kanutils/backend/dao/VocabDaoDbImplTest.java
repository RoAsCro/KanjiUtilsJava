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
        System.out.println(wordId);
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
        System.out.println(wordId);
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
        
    }

}