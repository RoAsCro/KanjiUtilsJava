package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.dto.Word;

import java.util.List;

public interface VocabDao {
    List<Word> getWords();
    Word getWord(String jpWord);

    /**
     * Adding a word that already exists should not update tags
     * TODO - add an edit function
     * @param word the word to be added
     */
    void addWord(Word word);
}
