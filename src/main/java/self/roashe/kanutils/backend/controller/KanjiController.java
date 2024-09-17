package self.roashe.kanutils.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.roashe.kanutils.backend.model.Kanji;
import self.roashe.kanutils.backend.service.KanjiService;

import java.util.List;

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
}
