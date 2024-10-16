package self.roashe.kanutils.backend.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;
import self.roashe.kanutils.backend.dto.Word;

import java.util.List;

public class ImportDaoKanshudoImplTest {

    ImportDao dao = new ImportDaoImplKanshudoImpl();

    @Test
    public void testWordCreation(){
        final boolean RUN = false;
        if (!RUN) {
            return;
        }
        List<Word> words = dao.getAllWords("rollycrompton@gmail.com", "7xE3Esfim59SYSv");

        for (Word word : words) {
            Assertions.assertTrue(JapaneseLanguageUtil.containsJapanese(word.getJapanese(), true), word.toString());
            for (String reading : word.getReadings())
                Assertions.assertTrue(JapaneseLanguageUtil.containsJapanese(reading, true), word.toString());
            for (String definition : word.getEnglish())
                Assertions.assertFalse(JapaneseLanguageUtil.containsJapanese(definition, false), word.toString());
        }

    }


}
