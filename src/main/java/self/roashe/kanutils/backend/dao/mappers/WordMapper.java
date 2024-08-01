package self.roashe.kanutils.backend.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import self.roashe.kanutils.backend.model.Word;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WordMapper implements RowMapper<Word> {

    @Override
    public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
        Word word = new Word();
        word.setJapanese(rs.getString("japanese-word"));
        return word;
    }
}
