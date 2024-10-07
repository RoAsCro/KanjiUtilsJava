package self.roashe.kanutils.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import self.roashe.kanutils.backend.dto.Word;
import self.roashe.kanutils.backend.service.IOExceptions.KanjiIOException;
import self.roashe.kanutils.backend.service.VocabService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/vocab")
public class VocabController {
    @Autowired
    VocabService vocabService;

    @PostMapping("/import")
    public void importWords(@RequestParam String username, @RequestParam String password) {
        this.vocabService.importAndStore(username, password);
    }

    @PutMapping("/tagkanawords")
    public void tagKana(){

    }

    @GetMapping("/vocab")
    public List<Word> getWords(@RequestParam(required = false) String[] tags){
        if (tags != null && tags.length != 0) {
            return this.vocabService.getByTags(Arrays.asList(tags));
        }
        return this.vocabService.getAllVocab();
    }

    @GetMapping("/kanjiextract")
    public void extractKanji() {
        try {
            this.vocabService.extractKanjiFromVocab();
        } catch (KanjiIOException ignored) {
            //TODO
        }
    }


}
