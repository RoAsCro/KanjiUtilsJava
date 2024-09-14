package self.roashe.kanutils.backend;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JapaneseLanguageUtil {
    public static final char FIRST_HIRAGANA = 'ぁ';
    public static final char LAST_HIRAGANA = 'ゖ';
    public static final char FIRST_KATAKANA = '゠';
    public static final char LAST_KATAKANA = 'ヿ';

    public static final String HIRAGANA_REGEX = "[" + FIRST_HIRAGANA + "-" + LAST_HIRAGANA + "]+";
    public static final String KATAKANA_REGEX = "[" + FIRST_KATAKANA + "-" + LAST_KATAKANA + "]+";
    public static final String KANA_REGEX = "[" + FIRST_HIRAGANA + "-" + LAST_HIRAGANA +
            FIRST_KATAKANA + "-" + LAST_KATAKANA + "]+";
    public static final String KANJI_REGEX = "[㐀-䶵一-鿋豈-頻]+";
    public static final String JAPANESE_REGEX = "[ぁ-ゖ゠-ヿ㐀-䶵一-鿋豈-頻々]+";

    public static boolean containsHiragana(String text, boolean only) {
        return matchPattern(text, HIRAGANA_REGEX, only);
    }

    public static boolean containsKatakana(String text, boolean only) {
        return matchPattern(text, KATAKANA_REGEX, only);
    }

    public static boolean containsKana(String text, boolean only) {
        return matchPattern(text, KANA_REGEX, only);
    }

    public static boolean containsKanji(String text, boolean only) {
        return matchPattern(text, KANJI_REGEX, only);
    }

    public static boolean containsJapanese(String text, boolean only) {
        return matchPattern(text, JAPANESE_REGEX, only);
    }

    public static boolean kanaEquivalence(String expected, String actual) {
        if (expected.equals(actual)) {
            return true;
        }
        System.out.println(actual.chars());
//        Arrays.stream(actual.split("")).map(s -> )
        return true;
    }

    private static boolean matchPattern(String text, String regex, boolean only) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return only ? matcher.matches() : matcher.find();
    }

}
