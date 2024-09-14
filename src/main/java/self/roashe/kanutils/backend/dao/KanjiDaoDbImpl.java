package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;
import self.roashe.kanutils.backend.dao.mappers.KanjiMapper;
import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.model.Word;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class KanjiDaoDbImpl implements KanjiDao{

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void addKanji(Kanji kanji) {
        final String GET_KANJI = "SELECT kanji FROM kanji " +
                "WHERE kanji = ?";

        String kanjiAsString = kanji.getKanji() + "";
        List<String> found = this.jdbc.queryForList(GET_KANJI, String.class, kanjiAsString);
        if (!found.isEmpty()) {
            return;
        }

        final String ADD_KANJI = "INSERT INTO kanji(kanji) VALUES(?)";
        this.jdbc.update(ADD_KANJI, kanjiAsString);


    }

    @Override
    public List<Kanji> getAllKanji() {
        final String GET_KANJI = "SELECT * FROM kanji";
        List<Kanji> kanjiList = this.jdbc.query(GET_KANJI, new KanjiMapper());

        for (Kanji kanji : kanjiList) {
            constructKanji(kanji);
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

    private void constructKanji(Kanji kanji) {
        getEnglish(kanji);
        getKunReadings(kanji);
        getOnReadings(kanji);
    }

    private void getKunReadings(Kanji kanji) {
        final String GET_KUN = "SELECT reading FROM kunreading " +
                "INNER JOIN kanji_has_kun ON kunreading_kunID " +
                "WHERE kanji_kanjiID = ?";
        List<String> readings = this.jdbc.queryForList(GET_KUN, String.class, kanji.getId());
        kanji.setKunReadings(readings);
    }

    private void getOnReadings(Kanji kanji) {
        final String GET_KUN = "SELECT reading FROM onreading " +
                "INNER JOIN kanji_has_on ON onreading_onID " +
                "WHERE kanji_kanjiID = ?";
        List<String> readings = this.jdbc.queryForList(GET_KUN, String.class, kanji.getId());
        kanji.setOnReadings(readings);
    }

    private void getEnglish(Kanji kanji) {
        final String GET_MEANING = "SELECT meaning FROM kanjimeaning " +
                "WHERE kanji_kanjiID = ?";
        List<String> english = this.jdbc.queryForList(GET_MEANING, String.class, kanji.getId());
        kanji.setEnglish(english);
    }

}
