package self.roashe.kanutils.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.roashe.kanutils.backend.dao.KanjiDao;
import self.roashe.kanutils.backend.dao.WebConnection.KanjiApiUtil;
import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;

import java.io.IOException;

import static self.roashe.kanutils.backend.JapaneseLanguageUtil.KANJI_REGEX;
@Service
public class KanjiServiceImpl implements KanjiService {

    @Autowired
    KanjiDao dao;

    @Override
    public void addKanji(String textContainingKanji) throws KanjiIOException {
        String onlyKanji = textContainingKanji.replaceAll("[^" + KANJI_REGEX.substring(1), "");
        char[] kanjiArray = onlyKanji.toCharArray();

        for (char k : kanjiArray) {
            Kanji kanjiToAdd;
            try {
                kanjiToAdd = KanjiApiUtil.getKanjiFromAPI(k);
            } catch (IOException | InterruptedException e) {
                throw new KanjiIOException("Kanji Coulld Not Be  Found");
            }
            this.dao.addKanji(kanjiToAdd);
        }
    }

    @Override
    public void export() {
        this.dao.export();
    }

}
