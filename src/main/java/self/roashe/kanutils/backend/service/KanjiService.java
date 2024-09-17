package self.roashe.kanutils.backend.service;

import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;

public interface KanjiService {
    void addKanji(String textContainingKanji) throws KanjiIOException;

}
