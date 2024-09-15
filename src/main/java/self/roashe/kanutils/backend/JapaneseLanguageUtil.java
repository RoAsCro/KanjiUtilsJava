package self.roashe.kanutils.backend;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JapaneseLanguageUtil {
    public static final char FIRST_HIRAGANA = 'ぁ';
    public static final char LAST_HIRAGANA = 'ゖ';
    public static final char FIRST_KATAKANA = '゠';
    public static final char LAST_KATAKANA = 'ヿ';

    private static final int KANA_DIFF = 96;

    public static final String HIRAGANA_REGEX = "[" + FIRST_HIRAGANA + "-" + LAST_HIRAGANA + "]+";
    public static final String KATAKANA_REGEX = "[" + FIRST_KATAKANA + "-" + LAST_KATAKANA + "]+";
    public static final String KANA_REGEX = "[" + FIRST_HIRAGANA + "-" + LAST_HIRAGANA +
            FIRST_KATAKANA + "-" + LAST_KATAKANA + "]+";
    public static final String KANJI_REGEX = "[㐀-䶵一-鿋豈-頻]+";
    public static final String JAPANESE_REGEX = "[ぁ-ゖ゠-ヿ㐀-䶵一-鿋豈-頻々]+";
    public static final String NOT_KANA_REGEX = "[^あ-んア-ン]+";

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

    /**
     * Tests that the kana in one String is equivalent to the kana of another. Returns true if so, otherwise false.
     * <p></p>
     * The text can contain non-kana characters, but may produce false positives for these.
     * @param expected the first String
     * @param actual the second String
     * @return true if the kana in the texts are equivalent, false otherwise
     */
    public static boolean kanaEquivalence(String expected, String actual) {
        if (expected.length() != actual.length()) {
            return false;
        }

        if (expected.equals(actual)) {
            return true;
        }

        Iterator<Integer> actualIter = actual.chars().iterator();
        Iterator<Integer> expectedIter = expected.chars().iterator();

        while (actualIter.hasNext() && expectedIter.hasNext()) {
            int i = actualIter.next();
            int j = expectedIter.next();
            if (i != j && Math.abs(i - j) !=  KANA_DIFF) {
                return false;
            }
        }

        return true;
    }

    public static String hiraganise(String text) {
        final String HIRAGANA = "[あ-ん]+";
        final String KATAKANA = "[ア-ン]+";
        Pattern pattern = Pattern.compile("[あ-ん]+");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            String foundText = matcher.group();
            String newText = foundText
                    .chars()
                    .mapToObj(c -> ((char)(c+KANA_DIFF)) + "")
                    .collect(Collectors.joining());
            text = text.replaceAll(foundText, newText);
        }


        String notKana = "[^あ-んア-ン]+";
        return text;
    }

    private static boolean matchPattern(String text, String regex, boolean only) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        return only ? matcher.matches() : matcher.find();
    }

}
