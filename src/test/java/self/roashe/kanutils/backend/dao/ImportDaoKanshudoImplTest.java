package self.roashe.kanutils.backend.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;
import self.roashe.kanutils.backend.model.Word;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportDaoKanshudoImplTest {

    ImportDao dao = new ImportDaoImplKanshudoImpl();

    @Test
    public void testWordCreation(){
        List<Word> words = dao.getAllWords("rollycrompton@gmail.com", "7xE3Esfim59SYSv");

        for (Word word : words) {
            Assertions.assertTrue(JapaneseLanguageUtil.containsJapanese(word.getJapanese(), true), word.toString());
            for (String reading : word.getReadings())
                Assertions.assertTrue(JapaneseLanguageUtil.containsKana(reading, true), word.toString());
            for (String definition : word.getEnglish())
                Assertions.assertFalse(JapaneseLanguageUtil.containsJapanese(definition, false), word.toString());
        }

    }

    @Test
    public void testPattern(){
        String testString = "ことにする\tPay attention to the past tense meaning ことにする ことにする to decide to; to pretend that; to make a practice of doing; to make it a rule to";
        Pattern pattern = Pattern.compile("[a-zA-Z]|[(-]");
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.find());
        Assertions.assertDoesNotThrow(() -> matcher.start());
    }

}
