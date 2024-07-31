package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.model.Word;

import java.util.List;

public interface ImportDao {

    List<Word> getAllWords(String username, String password);

}
