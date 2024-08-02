package self.roashe.kanutils.backend.dao;

import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;
import self.roashe.kanutils.backend.dao.WebConnection.SeleniumUtil;
import self.roashe.kanutils.backend.model.Word;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
@Repository
public class ImportDaoImplKanshudoImpl implements ImportDao {

    private static final String GRAMMAR_WARNING = "(text of grammar points is not available for download)";


    @Override
    public List<Word> getAllWords(String username, String password) {
        String rawVocab = SeleniumUtil.getFlashcards(username, password,
                "C:\\Users\\rolly\\Documents\\geckodriver-v0.34.0-win64\\geckodriver.exe");
        List<String> listVocab = stringToList(rawVocab);

        List<Word> wordList = listVocab.stream().map(this::stringToWord).collect(Collectors.toList());

        return wordList;
    }

    private List<String> stringToList(String allVocab) {
        return Arrays.stream(allVocab.split("\n"))
                .filter(s -> !s.contains(GRAMMAR_WARNING))
                .collect(Collectors.toList());
    }

    private Word stringToWord(String entry){
        Word word = new Word();

        int endOfVocabWord = entry.indexOf("\t");
        String vocabWord = entry.substring(0, endOfVocabWord);
        word.setJapanese(vocabWord);

        String back = entry.substring(endOfVocabWord + 1);
        word.setReadings(Arrays.stream(back.split(" "))
                .filter(s -> JapaneseLanguageUtil.containsJapanese(s, true))
                .map(String::strip)
                .collect(Collectors.toList()));

        String english = back.replaceAll(JapaneseLanguageUtil.JAPANESE_REGEX, "");
        word.setEnglish(Arrays.stream(english.split("; ")).collect(Collectors.toList()));

        return word;
    }

}
