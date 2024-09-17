package self.roashe.kanutils.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.roashe.kanutils.backend.dao.ImportDao;
import self.roashe.kanutils.backend.dao.VocabDao;
import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.model.Word;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;

import java.util.List;

@Service
public class VocabServiceImpl implements VocabService {

    @Autowired
    ImportDao importDao;

    @Autowired
    VocabDao vocabDao;

    @Autowired
    KanjiService kanjiService;

    @Override
    public void importAndStore(String username, String password) {
        List<Word> words = this.importDao.getAllWords(username, password);
        words.forEach(this.vocabDao::addWord);
    }

    @Override
    public List<Word> getAllVocab() {
        return this.vocabDao.getWords();
    }

    @Override
    public List<Kanji> getAllKanji() {
        return List.of();
    }

    @Override
    public void extractKanjiFromVocab() throws KanjiIOException {
        for (Word word : getAllVocab()) {
            this.kanjiService.addKanji(word.getJapanese());
        }
    }
}
