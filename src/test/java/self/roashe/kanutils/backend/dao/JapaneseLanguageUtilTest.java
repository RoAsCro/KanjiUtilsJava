package self.roashe.kanutils.backend.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JapaneseLanguageUtilTest {

    @Test
    public void testKanjiPattern() {
        String testString = "折水";
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.KANJI_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("A").matches());

    }

    @Test
    public void testKanaPattern() {
        String testString = "みずリンゴ";
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.KANA_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("A").matches());
    }

    @Test
    public void testKatakanaPattern() {
        String testString = "リンゴ";
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.KATAKANA_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("みず").matches());
    }

    @Test
    public void testHiraganaPattern() {
        String testString = "みず";
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.HIRAGANA_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("リンゴ").matches());
    }
}
