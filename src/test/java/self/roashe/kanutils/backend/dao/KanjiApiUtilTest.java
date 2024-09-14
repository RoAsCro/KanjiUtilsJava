package self.roashe.kanutils.backend.dao;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.dao.WebConnection.KanjiApiUtil;

import java.io.IOException;
import java.util.List;

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

    @Test
    void testThrowsWhenNotFoundKana() {
        assertThrows(IOException.class, () -> KanjiApiUtil.getKanji('く'));
    }

    @Test
    void testKanjiCorrect() throws IOException, InterruptedException {
        // Kanji
        assertEquals('角', KanjiApiUtil.getKanji('角').getKanji());

        // Kun
        assertEquals(List.of("かど", "つの"), KanjiApiUtil.getKanji('角').getKunReadings());

        // On
        assertEquals(List.of("カク"), KanjiApiUtil.getKanji('角').getOnReadings());

        // English
        assertEquals(List.of("angle", "antlers", "corner", "horn", "square"),
                KanjiApiUtil.getKanji('角').getEnglish());


    }

}
