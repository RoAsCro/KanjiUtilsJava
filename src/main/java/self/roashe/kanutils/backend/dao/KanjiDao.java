package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.model.Word;

import java.util.List;

public interface KanjiDao {
    void addKanji(String textContainingKanji);
    List<Kanji> getAllKanji();
    List<Kanji> getKanjiInString(String text);
    List<Kanji> getKanjiByReading(String reading);
    List<Word> getWordsByKanji(char kanji);
}
