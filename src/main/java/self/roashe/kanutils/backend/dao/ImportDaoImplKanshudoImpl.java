package self.roashe.kanutils.backend.dao;

import org.springframework.stereotype.Repository;
import self.roashe.kanutils.backend.JapaneseLanguageUtil;
import self.roashe.kanutils.backend.dao.web_utilities.SeleniumUtil;
import self.roashe.kanutils.backend.dto.Word;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class ImportDaoImplKanshudoImpl implements ImportDao {

    private static final String GRAMMAR_WARNING = "(text of grammar points is not available for download)";


    @Override
    public List<Word> getAllWords(String username, String password) {
        String rawVocab = SeleniumUtil.getFlashcards(username, password,
                "C:\\Users\\rolly\\OneDrive\\Documents\\geckodriver-v0.34.0-win64\\geckodriver.exe");
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
        String[] entryParts = entry.split("TAGS<");
        entry = entryParts[0];
        String tags = entryParts[1];
        word.setTags(Arrays.stream(
                tags.substring(0, tags.length() - 1)
                        .replaceAll("\\.", "")
                        .split(","))
                .collect(Collectors.toList()));

        int endOfVocabWord = entry.indexOf("\t");
        String vocabWord = entry.substring(0, endOfVocabWord);
        word.setJapanese(vocabWord);

        String back = entry.substring(endOfVocabWord + 1);
        word.setReadings(Arrays.stream(back.split(" "))
                .filter(s -> JapaneseLanguageUtil.containsJapanese(s, true))
                .map(String::strip)
                .collect(Collectors.toList()));

        String english = back.replaceAll(JapaneseLanguageUtil.JAPANESE_REGEX, "");
        word.setEnglish(Arrays.stream(english.split("; ")).map(String::strip).collect(Collectors.toList()));
        return word;
    }

}
