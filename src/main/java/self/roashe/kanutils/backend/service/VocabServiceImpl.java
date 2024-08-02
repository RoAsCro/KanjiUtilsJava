package self.roashe.kanutils.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.roashe.kanutils.backend.dao.ImportDao;
import self.roashe.kanutils.backend.dao.VocabDao;
import self.roashe.kanutils.backend.model.Word;

import java.util.List;

@Service
public class VocabServiceImpl implements VocabService {

    @Autowired
    ImportDao importDao;

    @Autowired
    VocabDao vocabDao;

    @Override
    public void importAndStore(String username, String password) {
        List<Word> words = this.importDao.getAllWords(username, password);
        words.forEach(this.vocabDao::addWord);
    }

    @Override
    public List<Word> getAllVocab() {
        return this.vocabDao.getWords();
    }
}
