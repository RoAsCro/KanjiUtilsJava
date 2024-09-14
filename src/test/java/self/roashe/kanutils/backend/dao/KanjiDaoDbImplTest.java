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
class KanjiDaoDbImplTest {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    KanjiDao dao;

    @BeforeEach
    void setUp() {
        jdbc.update("DELETE FROM kanji_has_kun");
        jdbc.update("DELETE FROM kanji_has_on");
        jdbc.update("DELETE FROM kanjimeaning");
        jdbc.update("DELETE FROM kanji");
        jdbc.update("DELETE FROM kunreading");
        jdbc.update("DELETE FROM onreading");

        jdbc.update("INSERT INTO kanji(kanji) VALUES(?)", "増");
        Integer kanjiID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        jdbc.update("INSERT INTO kunreading(reading) VALUES(?)", "ふ.える");
        Integer kunID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        jdbc.update("INSERT INTO onreading(reading) VALUES(?)", "ゾウ");
        Integer onID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        jdbc.update("INSERT INTO kanjimeaning(meaning, kanji_kanjiID) VALUES(?, ?)", "add", kanjiID);
        jdbc.update("INSERT INTO kanji_has_kun(kanji_kanjiID, kunreading_kunID) VALUES(?, ?)", kanjiID, kunID);
        jdbc.update("INSERT INTO kanji_has_on(kanji_kanjiID, onreading_onID) VALUES(?, ?)", kanjiID, onID);
    }

    @Test
    public void testAutowiring(){
        Assertions.assertNotNull(this.dao);
    }

    @Test
    public void testGetAll(){
        
    }

}