package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.model.Word;

import java.util.List;

public interface KanjiDao {
    void addKanji(Kanji kanji);
    List<Kanji> getAllKanji();
    List<Kanji> getKanjiByReading(String reading);
    List<Word> getWordsByKanji(char kanji);
}
