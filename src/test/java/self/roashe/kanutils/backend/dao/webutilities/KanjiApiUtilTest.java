package self.roashe.kanutils.backend.dao.webutilities;

import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.dao.web_utilities.KanjiApiUtil;
import self.roashe.kanutils.backend.dto.Kanji;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KanjiApiUtilTest {


    @Test
    @Disabled
    void testNoException() throws JSONException, InterruptedException, IOException {
        assertDoesNotThrow(() -> KanjiApiUtil.getKanjiFromAPI('角'));
    }

    @Test
    @Disabled
    void testReturnsNotNull() throws JSONException, IOException, InterruptedException {
        assertNotNull(KanjiApiUtil.getKanjiFromAPI('角'));
    }

    @Test
    @Disabled
    void testThrowsWhenNotFound() {
        assertThrows(IOException.class, () -> KanjiApiUtil.getKanjiFromAPI('k'));
    }

    @Test
    @Disabled
    void testThrowsWhenNotFoundKana() {
        assertThrows(IOException.class, () -> KanjiApiUtil.getKanjiFromAPI('く'));
    }

    @Test
    @Disabled
    void testKanjiCorrect() throws IOException, InterruptedException {
        // Kanji
        assertEquals('角', KanjiApiUtil.getKanjiFromAPI('角').getKanji());

        // Kun
        assertEquals(List.of("かど", "つの"), KanjiApiUtil.getKanjiFromAPI('角').getKunReadings());

        // On
        assertEquals(List.of("カク"), KanjiApiUtil.getKanjiFromAPI('角').getOnReadings());

        // English
        assertEquals(List.of("angle", "antlers", "corner", "horn", "square"),
                KanjiApiUtil.getKanjiFromAPI('角').getEnglish());


    }

    @Test
    void testGetKanjiFromFile() throws IOException, JSONException {
        assertEquals('角', KanjiApiUtil.getKanjiFromInternalFile('角').getKanji());
        // Kun
        assertEquals(List.of("かど", "つの"), KanjiApiUtil.getKanjiFromInternalFile('角').getKunReadings());

        // On
        assertEquals(List.of("カク"), KanjiApiUtil.getKanjiFromInternalFile('角').getOnReadings());

        // English
        assertEquals(List.of("angle", "antlers", "corner", "horn", "square"),
                KanjiApiUtil.getKanjiFromInternalFile('角').getEnglish());
    }

    @Test
    void testGetKanjiFromFileNonKanji() throws FileNotFoundException, JSONException {
        assertThrows(IOException.class, () -> KanjiApiUtil.getKanjiFromInternalFile('e'));
    }

    @Test
    @Disabled
    void testGetKanjiFromFileVSAPI() throws IOException, InterruptedException {
        Kanji kanji1 = KanjiApiUtil.getKanjiFromInternalFile('角');
        Kanji kanji2 = KanjiApiUtil.getKanjiFromAPI('角');
        assertEquals(kanji2, kanji1);
    }
}
