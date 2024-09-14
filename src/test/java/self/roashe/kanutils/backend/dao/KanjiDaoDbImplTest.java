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
import self.roashe.kanutils.backend.model.Kanji;

import java.util.List;

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

        jdbc.update("INSERT INTO kanji(kanji) VALUES(?)", "水");
    }

    @Test
    public void testAutowiring(){
        Assertions.assertNotNull(this.dao);
    }

    @Test
    public void testGetAll(){
        List<Kanji> kanji = this.dao.getAllKanji();
        assertEquals(2, kanji.size());
        Kanji fueru = kanji.stream().filter(k -> k.getKanji() == '増').findAny().orElse(null);
        assertNotNull(fueru);
        assertEquals(List.of("ふ.える"), fueru.getKunReadings());
        assertEquals(List.of("ゾウ"), fueru.getOnReadings());
        assertEquals(List.of("add"), fueru.getEnglish());
    }

    @Test
    public void testGetAllMissingParts(){
        List<Kanji> kanji = this.dao.getAllKanji();
        assertEquals(2, kanji.size());
        Kanji mizu = kanji.stream().filter(k -> k.getKanji() == '水').findAny().orElse(null);
        assertNotNull(mizu);
        assertEquals(List.of(), mizu.getOnReadings());
        assertEquals(List.of(), mizu.getKunReadings());
        assertEquals(List.of(), mizu.getEnglish());
    }

    @Test
    public void testAddExists(){
        Kanji kanji = new Kanji();
        kanji.setKanji('増');
        this.dao.addKanji(kanji);
        assertEquals(2, this.dao.getAllKanji().size());
    }

    @Test
    public void testAddDoesNotExist(){
        Kanji kanji = new Kanji();
        kanji.setKanji('日');
        kanji.setKunReadings(List.of());
        kanji.setOnReadings(List.of());
        kanji.setEnglish(List.of());
        this.dao.addKanji(kanji);
        assertEquals(3, this.dao.getAllKanji().size());
    }

    @Test
    public void testValuesAdded(){
        Kanji kanji = new Kanji();
        kanji.setKanji('日');

        List<String> kunReadings = List.of("ひ", "にち");
        kanji.setKunReadings(kunReadings);

        List<String> onReadings = List.of("タチ", "ニ");
        kanji.setOnReadings(onReadings);

        List<String> meanings = List.of("day", "sun");
        this.dao.addKanji(kanji);
        List<Kanji> getAll = dao.getAllKanji();
        Kanji retrievedKanji = this.dao.getAllKanji()
                .stream()
                .filter(k -> kanji.getKanji() == k.getKanji())
                .findAny()
                .orElse(null);
        assertNotNull(retrievedKanji);

        assertEquals(kunReadings, retrievedKanji.getKunReadings());
        assertEquals(onReadings, retrievedKanji.getOnReadings());
//        assertEquals(meanings, kanji.getEnglish());
    }

}