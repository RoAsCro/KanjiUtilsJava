package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.model.Word;

import java.util.List;

public interface VocabDao {
    List<Word> getWords();
    Word getWord(String jpWord);
    void addWord(Word word);
}
