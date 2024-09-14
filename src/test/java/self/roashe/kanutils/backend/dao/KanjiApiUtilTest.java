package self.roashe.kanutils.backend.dao;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.dao.WebConnection.KanjiApiUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class KanjiApiUtilTest {


    @Test
    void testNoException() throws JSONException, InterruptedException, IOException {
        assertDoesNotThrow(() -> KanjiApiUtil.getKanji('角'));
    }

    @Test
    void testReturnsNotNull() throws JSONException, IOException, InterruptedException {
        assertNotNull(KanjiApiUtil.getKanji('角'));
    }

    @Test
    void testThrowsWhenNotFound() {
        assertThrows(IOException.class, () -> KanjiApiUtil.getKanji('k'));
    }

    

}
