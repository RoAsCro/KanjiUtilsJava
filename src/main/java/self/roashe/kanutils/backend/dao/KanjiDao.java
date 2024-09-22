package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.dto.Kanji;
import self.roashe.kanutils.backend.dto.Word;

import java.util.List;

public interface KanjiDao {
    void addKanji(Kanji kanji);
    List<Kanji> getAllKanji();
    List<Kanji> getKanjiByReading(String reading);
    List<Word> getWordsByKanji(char kanji);
    void export();
    void clearLocalData();
}
