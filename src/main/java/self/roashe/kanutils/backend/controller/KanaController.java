package self.roashe.kanutils.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import self.roashe.kanutils.backend.JapaneseLanguageUtil;

@RestController
@CrossOrigin
@RequestMapping("/api/kana")
public class KanaController {
    
    @PostMapping("/katakanise/{text}")
    public String convertKana(@PathVariable("text") String text) {
        return JapaneseLanguageUtil.katakanise(text);
    }
}
