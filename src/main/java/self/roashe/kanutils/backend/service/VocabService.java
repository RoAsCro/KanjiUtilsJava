package self.roashe.kanutils.backend.service;


import self.roashe.kanutils.backend.dto.Word;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;
import self.roashe.kanutils.backend.service.IOExceptions.VocabIOException;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface VocabService {
    void importAndStore(String username, String password);
    List<Word> getAllVocab();
    List<Word> getByTags(List<String> tags);
    void extractKanjiFromVocab() throws KanjiIOException;

    void updateByCondition(Consumer<Word> update, Predicate<Word> condition) throws VocabIOException;

    /**
     * <p>Does not accept:</p>
     * <li>[.,<>] for tags</li>
     * @param word the word being updated
     */
    void update(Word word) throws VocabIOException;
}
