package self.roashe.kanutils.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import self.roashe.kanutils.backend.model.Word;
import self.roashe.kanutils.backend.service.VocabService;

import java.util.List;

@RestController
@RequestMapping("/api/vocab")
public class VocabController {
    @Autowired
    VocabService vocabService;

    @PostMapping("/import")
    public void importWords(@RequestParam String username, @RequestParam String password) {
        this.vocabService.importAndStore(username, password);
    }

    @GetMapping("/vocab")
    public List<Word> getWords(){
        return this.vocabService.getAllVocab();
    }

}
