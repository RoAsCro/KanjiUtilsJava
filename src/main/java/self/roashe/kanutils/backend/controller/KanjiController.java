package self.roashe.kanutils.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.roashe.kanutils.backend.dto.Kanji;
import self.roashe.kanutils.backend.dto.Word;
import self.roashe.kanutils.backend.service.KanjiService;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kanji")
public class KanjiController {

    @Autowired
    KanjiService service;

    @GetMapping("/all")
    public List<Kanji> getKanji() {
        this.service.getKanji().forEach(k -> System.out.println(k.getKanji()));
        return this.service.getKanji();
    }

    @GetMapping("/all/aswords")
    public List<Word> getKanjiAsWords() {
        return getKanji().stream().map(k -> {
            Word word = new Word();
            word.setJapanese(k.getKanji() + "");
            word.setEnglish(k.getEnglish());
            List<String> allReadings = new LinkedList<>(k.getOnReadings());
            allReadings.addAll(k.getKunReadings());
            word.setReadings(allReadings);
            return word;
        }).collect(Collectors.toList());
    }
}
