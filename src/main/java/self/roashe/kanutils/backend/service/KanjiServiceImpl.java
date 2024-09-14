package self.roashe.kanutils.backend.service;

import self.roashe.kanutils.backend.JapaneseLanguageUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KanjiServiceImpl implements KanjiService {
    @Override
    public void addKanji(String textContainingKanji) {
        List<String> kanji = Arrays.stream(textContainingKanji.split(""))
                .filter(s ->
                        JapaneseLanguageUtil.containsKanji(s, true))
                .collect(Collectors.toList());
        // TODO
    }
}
