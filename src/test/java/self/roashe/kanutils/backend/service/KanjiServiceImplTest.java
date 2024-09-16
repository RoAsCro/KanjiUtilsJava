package self.roashe.kanutils.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import self.roashe.kanutils.backend.TestApplicationConfiguration;
import self.roashe.kanutils.backend.dao.KanjiDao;
import self.roashe.kanutils.backend.dao.WebConnection.KanjiApiUtil;
import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class KanjiServiceImplTest {

    @Autowired
    KanjiService service;
    @Autowired
    KanjiDao dao;
    @Autowired
    JdbcTemplate jdbc;

    @BeforeEach
    void setup(){
        jdbc.update("DELETE FROM kanji_has_kun");
        jdbc.update("DELETE FROM kanji_has_on");
        jdbc.update("DELETE FROM kanjimeaning");
        jdbc.update("DELETE FROM kanji");
        jdbc.update("DELETE FROM kunreading");
        jdbc.update("DELETE FROM onreading");
    }

    @Test
    public void testAddKanji() throws KanjiIOException {
        this.service.addKanji("水");
        assertEquals(1, dao.getAllKanji().size());
    }

    @Test
    public void testAddKanjiDataSet() throws KanjiIOException, IOException, InterruptedException {
        this.service.addKanji("水");
        List<Kanji> kanjiList = dao.getAllKanji();
        assertEquals(1, kanjiList.size());
        Kanji kanji = kanjiList.get(0);
        assertEquals(KanjiApiUtil.getKanji('水'), kanji);
    }

    @Test
    public void testAddKanjiMultiple() throws KanjiIOException, IOException, InterruptedException {
        this.service.addKanji("水日");
        List<Kanji> kanjiList = dao.getAllKanji();
        assertEquals(2, kanjiList.size());

        assertEquals(List.of(KanjiApiUtil.getKanji('水'),
                KanjiApiUtil.getKanji('日')),
                dao.getAllKanji());
    }

    @Test
    public void testAddKanjiNoKanji() throws KanjiIOException {
        this.service.addKanji("test");
        List<Kanji> kanjiList = dao.getAllKanji();
        assertEquals(0, kanjiList.size());
    }

    @Test
    public void testAddKanjiMixedText() throws KanjiIOException, IOException, InterruptedException {
        this.service.addKanji("mizuwater水ぞeくasd日あｓ");
        List<Kanji> kanjiList = dao.getAllKanji();
        assertEquals(2, kanjiList.size());

        assertEquals(List.of(KanjiApiUtil.getKanji('水'),
                        KanjiApiUtil.getKanji('日')),
                dao.getAllKanji());
    }


}