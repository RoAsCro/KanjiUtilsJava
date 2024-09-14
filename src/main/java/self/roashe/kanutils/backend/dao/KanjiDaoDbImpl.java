package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.dao.mappers.KanjiMapper;
import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.model.Word;

import java.util.List;

@Repository
public class KanjiDaoDbImpl implements KanjiDao{

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void addKanji(String textContainingKanji) {

    }

    @Override
    public List<Kanji> getAllKanji() {
        final String GET_KANJI = "SELECT * FROM kanji";
        List<Kanji> kanjiList = this.jdbc.query(GET_KANJI, new KanjiMapper());

        for (Kanji kanji : kanjiList) {
            getKunReadings(kanji);
        }

        return kanjiList;
    }

    @Override
    public List<Kanji> getKanjiInString(String text) {
        return List.of();
    }

    @Override
    public List<Kanji> getKanjiByReading(String reading) {
        return List.of();
    }

    @Override
    public List<Word> getWordsByKanji(char kanji) {
        return List.of();
    }

    private void getKunReadings(Kanji kanji) {
        final String GET_KUN = "SELECT reading FROM kunreading " +
                "INNER JOIN kanji_has_kun ON kunreading_kunID " +
                "WHERE kanji_kanjiID = ?";
        List<String> readings = this.jdbc.queryForList(GET_KUN, String.class, kanji.getId());
        kanji.setKunReadings(readings);
    }

}
