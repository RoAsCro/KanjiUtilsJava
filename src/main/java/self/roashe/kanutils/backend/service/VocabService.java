package self.roashe.kanutils.backend.service;


import self.roashe.kanutils.backend.dto.Word;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;

import java.util.List;

public interface VocabService {
    void importAndStore(String username, String password);
    List<Word> getAllVocab();
    List<Word> getByTags(List<String> tags);
    void extractKanjiFromVocab() throws KanjiIOException;

    /**
     * <p>Does not accept:</p>
     * <li>[.,<>] for tags</li>
     * @param word the word being updated
     */
    void update(Word word);
}
