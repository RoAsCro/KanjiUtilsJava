package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.model.Word;

import java.util.List;

public interface VocabDao {
    List<Word> getWords();
    void addWord(Word word);
}
