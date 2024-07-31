package self.roashe.kanutils.backend.dao;

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

public class ImportDaoImplKanshudoImpl implements ImportDao {

    private static final String GRAMMAR_WARNING = "(text of grammar points is not available for download)";


    @Override
    public List<Word> getAllWords(String username, String password) {
        String rawVocab = SeleniumUtil.getFlashcards(username, password,
                "C:\\Users\\rolly\\Documents\\geckodriver-v0.34.0-win64\\geckodriver.exe");
        List<String> listVocab = stringToList(rawVocab);

        List<Word> wordList = listVocab.stream().map(this::stringToWord).toList();

        return wordList;
    }

    private List<String> stringToList(String allVocab) {
        return Arrays.stream(allVocab.split("\n"))
                .filter(s -> !s.contains(GRAMMAR_WARNING))
                .toList();
    }

    private Word stringToWord(String entry){
        Word word = new Word();

        int endOfVocabWord = entry.indexOf("\t");
        String vocabWord = entry.substring(0, endOfVocabWord);
        word.setJapanese(vocabWord);

        System.out.println(entry);
        Pattern pattern = Pattern.compile("[a-zA-Z]|[(-]");
        Matcher matcher = pattern.matcher(entry);
        matcher.find();
        int englishIndex = matcher.start();

        String readings = entry.substring(endOfVocabWord + 1, englishIndex);
        word.setReadings(Arrays.stream(readings.split(" "))
                .map(String::strip)
                .toList());

        String english = entry.substring(englishIndex);
        word.setEnglish(Arrays.stream(english.split("; ")).toList());

        return word;
    }

}
