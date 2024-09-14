package self.roashe.kanutils.backend.dao;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.dao.WebConnection.KanjiApiUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KanjiApiUtilTest {


    @Test
    void testNoException() throws JSONException, InterruptedException, IOException {
        KanjiApiUtil.getKanji('角');
    }

}
