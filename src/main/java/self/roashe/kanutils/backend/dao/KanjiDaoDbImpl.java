package self.roashe.kanutils.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;
import self.roashe.kanutils.backend.dao.mappers.KanjiMapper;
import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.model.Word;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class KanjiDaoDbImpl implements KanjiDao{

    private static final String GET_LAST_ID = "SELECT LAST_INSERT_ID()";

    private final Set<Character> newKanji = new HashSet<>();

    private final Map<Character, Kanji> kanjiMap = new HashMap<>();

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void addKanji(Kanji kanji) {
        getAllKanji();
        if (this.kanjiMap.put(kanji.getKanji(), kanji) == null){
            newKanji.add(kanji.getKanji());
        }
    }

    @Override
    public List<Kanji> getAllKanji() {
        List<Kanji> kanjiList;
        if (this.kanjiMap.isEmpty()) {
            final String GET_KANJI = "SELECT * FROM kanji";
            kanjiList = this.jdbc.query(GET_KANJI, new KanjiMapper());
            for (Kanji kanji : kanjiList) {
                constructKanji(kanji);
                this.kanjiMap.put(kanji.getKanji(), kanji);
            }

        }
        kanjiList = List.copyOf(this.kanjiMap.values());


        return kanjiList;
    }

    @Override
    public List<Kanji> getKanjiByReading(String reading) {
        final String KANJI_BY_KUN_READING = "SELECT * FROM kanji " +
                "INNER JOIN kanji_has_kun ON kanji_kanjiID = kanjiID " +
                "INNER JOIN kunreading ON kunreading_kunID = kunID " +
                "WHERE reading = ?";
        Set<Kanji> kanji = new HashSet<>(jdbc.query(KANJI_BY_KUN_READING, new KanjiMapper(),
                JapaneseLanguageUtil.hiraganise(reading)));

        final String KANJI_BY_ON_READING = "SELECT * FROM kanji " +
                "INNER JOIN kanji_has_on ON kanji_kanjiID = kanjiID " +
                "INNER JOIN onreading ON onreading_onID = onID " +
                "WHERE reading = ?";

        kanji.addAll(jdbc.query(KANJI_BY_ON_READING, new KanjiMapper(),
                JapaneseLanguageUtil.katakanise(reading)));

        for (Kanji ji : kanji) {
            constructKanji(ji);
        }

        return List.copyOf(kanji);
    }

    @Override
    public List<Word> getWordsByKanji(char kanji) {
        return List.of();
    }

    @Override
    public void export() {
        // TODO consider deletion as a function
        for (Character character : this.newKanji) {
            Kanji kanji = this.kanjiMap.get(character);
            final String ADD_KANJI = "INSERT INTO kanji(kanji) VALUES(?)";
            this.jdbc.update(ADD_KANJI, kanji.getKanji() + "");
            Integer kanjiID = this.jdbc.queryForObject(GET_LAST_ID, Integer.class);
            kanji.setId(kanjiID);
            insertReading(kanji, kanji.getKunReadings(), "kun");
            insertReading(kanji, kanji.getOnReadings(), "on");
            insertMeanings(kanji);
        }
    }

    @Override
    public void clearLocalData() {
        this.kanjiMap.clear();
        this.newKanji.clear();
    }

    private void constructKanji(Kanji kanji) {
        getEnglish(kanji);
        getKunReadings(kanji);
        getOnReadings(kanji);
    }

    private void getKunReadings(Kanji kanji) {
        final String GET_KUN = "SELECT reading FROM kunreading " +
                "INNER JOIN kanji_has_kun ON kunreading_kunID = kunID " +
                "WHERE kanji_kanjiID = ?";
        List<String> readings = this.jdbc.queryForList(GET_KUN, String.class, kanji.getId());
        kanji.setKunReadings(readings);
    }

    private void getOnReadings(Kanji kanji) {
        final String GET_KUN = "SELECT reading FROM onreading " +
                "INNER JOIN kanji_has_on ON onreading_onID = onID " +
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

    private void insertReading(Kanji kanji, List<String> readings, String type) {
        for (String reading : readings) {
            int id;
            final String GET_READING = "SELECT "+ type +"ID FROM " + type + "reading " +
                    "WHERE reading = ?";

            List<Integer> singleReading = this.jdbc.queryForList(GET_READING, Integer.class, reading);
            if (!singleReading.isEmpty()) {
                id = singleReading.get(0);
            } else {
                final String INSERT_READING = "INSERT INTO " + type + "reading(reading) VALUES(?)";
                jdbc.update(INSERT_READING, reading);
                id = this.jdbc.queryForObject(GET_LAST_ID, Integer.class);
            }

            final String INSERT_KANJI_HAS_READING = "INSERT INTO kanji_has_" + type + "("
                    + type + "reading_" + type + "ID, kanji_kanjiID) " +
                    "VALUES(?, ?)";

            this.jdbc.update(INSERT_KANJI_HAS_READING, id, kanji.getId());
        }
    }

    private void insertMeanings(Kanji kanji) {
        for (String meaning : kanji.getEnglish()) {
            final String INSERT_MEANING = "INSERT INTO kanjimeaning(kanji_kanjiID, meaning) " +
                    "VALUES(?, ?)";
            jdbc.update(INSERT_MEANING, kanji.getId(), meaning);
        }
    }

}
