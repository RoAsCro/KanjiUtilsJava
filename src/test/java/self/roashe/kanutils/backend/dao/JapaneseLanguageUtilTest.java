package self.roashe.kanutils.backend.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static self.roashe.kanutils.backend.JapaneseLanguageUtil.*;

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
        String testString = FIRST_HIRAGANA + "みずリンゴ" + LAST_KATAKANA;
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.KANA_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("A").matches());
    }

    @Test
    public void testKatakanaPattern() {
        String testString = FIRST_KATAKANA +  "リンゴ" + LAST_KATAKANA;
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.KATAKANA_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("みず").matches());
    }

    @Test
    public void testHiraganaPattern() {
        String testString = FIRST_HIRAGANA + "みず" + LAST_HIRAGANA;
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.HIRAGANA_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("リンゴ").matches());
    }

    @Test
    public void testJapanesePattern() {
        String testString = FIRST_HIRAGANA + "みず鳥リンゴ" + LAST_KATAKANA;
        Pattern pattern = Pattern.compile(JapaneseLanguageUtil.JAPANESE_REGEX);
        Matcher matcher = pattern.matcher(testString);
        Assertions.assertTrue(matcher.matches());
        Assertions.assertFalse(pattern.matcher("English").matches());
    }

    @Test
    public void testJapaneseMatchingNoJapaneseNonOnlyMode() {
        String testString = "eigonosyouda";
        Assertions.assertFalse(JapaneseLanguageUtil.containsJapanese(testString, false));
    }

    @Test
    public void testJapaneseMatchingNoJapaneseNonOnlyModeTrue() {
        String testString = "eigonosyoudaく";
        Assertions.assertTrue(JapaneseLanguageUtil.containsJapanese(testString, false));
    }

    @Test
    public void testRepetitionMark() {
        String testString = "々";
        Assertions.assertTrue(JapaneseLanguageUtil.containsJapanese(testString, true));
    }

    @Test
    public void testKanaEquivalenceA(){
        Assertions.assertTrue(JapaneseLanguageUtil.kanaEquivalence("あ", "ア"));
    }

    @Test
    public void testKanaEquivalenceAFalse(){
        Assertions.assertFalse(JapaneseLanguageUtil.kanaEquivalence("あ", "ク"));
    }

    @Test
    public void testKanaEquivalenceSame(){
        Assertions.assertTrue(JapaneseLanguageUtil.kanaEquivalence("あ", "あ"));
    }

    @Test
    public void testKanaEquivalenceSameFalse(){
        Assertions.assertFalse(JapaneseLanguageUtil.kanaEquivalence("あ", "く"));
    }

    @Test
    public void testKanaEquivalenceAReverse(){
        Assertions.assertTrue(JapaneseLanguageUtil.kanaEquivalence("ア", "あ"));
    }

    @Test
    public void testKanaEquivalenceN(){
        Assertions.assertTrue(JapaneseLanguageUtil.kanaEquivalence("ん", "ン"));
    }

    @Test
    public void testKanaEquivalenceNReverse(){
        Assertions.assertTrue(JapaneseLanguageUtil.kanaEquivalence("ン", "ん"));
    }

    @Test
    public void testKanaEquivalenceMultiCharacter(){
        Assertions.assertTrue(JapaneseLanguageUtil.kanaEquivalence("たのしみます", "タノシミマス"));
    }

    @Test
    public void testKanaEquivalenceMultiCharacterDifferentTypes(){
        Assertions.assertTrue(JapaneseLanguageUtil.kanaEquivalence("たノしみマす", "タのしミまス"));
    }

    @Test
    public void test(){
        System.out.println(JapaneseLanguageUtil.hiraganise("a"));
        System.out.println(JapaneseLanguageUtil.hiraganise("aあぶ"));
        System.out.println(JapaneseLanguageUtil.hiraganise("あaんきにmafaw:{あ"));
    }
}
