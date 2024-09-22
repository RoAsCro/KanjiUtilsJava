package self.roashe.kanutils.backend.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import self.roashe.kanutils.backend.dto.Kanji;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KanjiMapper implements RowMapper<Kanji> {
    @Override
    public Kanji mapRow(ResultSet rs, int i) throws SQLException {
        Kanji kanji = new Kanji();
        kanji.setId(rs.getInt("kanjiId"));
        kanji.setKanji(rs.getString("kanji").charAt(0));
        return kanji;
    }
}
