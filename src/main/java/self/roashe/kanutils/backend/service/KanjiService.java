package self.roashe.kanutils.backend.service;

import self.roashe.kanutils.backend.dto.Kanji;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;

import java.util.List;

public interface KanjiService {
    void addKanji(String textContainingKanji) throws KanjiIOException;
    void export();
    List<Kanji> getKanji();
}
