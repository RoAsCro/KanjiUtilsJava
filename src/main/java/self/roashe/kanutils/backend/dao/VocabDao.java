package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.dto.Word;

import java.util.List;

public interface VocabDao {
    List<Word> getWords();
    Word getWord(String jpWord);

    Word getWordById(int id);

    /**
     * Adding a word that already exists should not update tags
     * @param word the word to be added
     */
    void addWord(Word word);

    void updateWord(Word word);

    void deleteWord(int id);
}
