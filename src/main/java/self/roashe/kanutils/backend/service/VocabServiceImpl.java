package self.roashe.kanutils.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;
import self.roashe.kanutils.backend.dao.ImportDao;
import self.roashe.kanutils.backend.dao.VocabDao;
import self.roashe.kanutils.backend.dto.Word;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;
import self.roashe.kanutils.backend.service.IOExceptions.VocabIOException;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Word> getByTags(List<String> tags) {
        return getAllVocab().stream()
                .filter(w -> w.getTags()
                        .stream()
                        .anyMatch(tags::contains))
                .collect(Collectors.toList());
    }

    @Override
    public void extractKanjiFromVocab() throws KanjiIOException {
        List<Word> vocab = getAllVocab();
        int count = 0;
        for (Word word : vocab) {
            try {
                count++;
                System.out.println(count + "/" + vocab.size());
                System.out.println(word);
                this.kanjiService.addKanji(word.getJapanese());
            } catch (DataIntegrityViolationException e) {
                System.out.println("ERROR");
                System.out.println(word);
            }
        }
        this.kanjiService.export();
    }

    @Override
    public void update(Word word) {
        validateWord(word);
        this.vocabDao.updateWord(word);
    }

    private boolean validateWord(Word word) {
        String jp = word.getJapanese();
        if (jp == null || jp.isEmpty()){
            throw new VocabIOException("Word needs Japanese.");
        }
        List<String> en = word.getEnglish();
        if (en == null || en.isEmpty()) {
            throw new VocabIOException("Word needs meanings.");
        }
        List<String> readings = word.getReadings();
        if (readings == null || readings.isEmpty()) {
            throw new VocabIOException("Word needs readings.");
        }
        List<String> tags = word.getTags();
        if (tags != null && !tags.isEmpty()) {
            if (tags.stream().anyMatch(t -> t.contains("[,.<>]"))){
                throw new VocabIOException("Tags may not contain any of the following characters:,.<>");
            }
        }
        return true;
    }

}
