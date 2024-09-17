package self.roashe.kanutils.backend.service;


import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.model.Word;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;

import java.util.List;

public interface VocabService {
    void importAndStore(String username, String password);
    List<Word> getAllVocab();
    List<Kanji> getAllKanji();
    void extractKanjiFromVocab() throws KanjiIOException;
}
