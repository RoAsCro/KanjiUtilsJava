package self.roashe.kanutils.backend.controller.pagescontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import self.roashe.kanutils.backend.dto.Word;
import self.roashe.kanutils.backend.service.VocabService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/words")
public class WordJSPController  {

    @Autowired
    private VocabService service;

    @GetMapping("/viewWords")
    public String viewWords(Model model) {
        model.addAttribute("vocab", this.service.getAllVocab());
        return "view-words";
    }

    @GetMapping("/readinggame")
    public String readingGame(Model  model,
                              @RequestParam(defaultValue = "true", name = "repeat") boolean repeat,
                              @RequestParam(required = false) String[] tags,
                              @RequestParam(defaultValue = "false") boolean hideEnglish,
                              @RequestParam(defaultValue = "false") boolean hideKanji) {
        return typing(model, false, repeat, false, hideEnglish,"Readings Test", tags);
    }

    @GetMapping("/wordgame")
    public String wordGame(Model  model,
                           @RequestParam(defaultValue = "true", name = "repeat") boolean repeat,
                           @RequestParam(required = false) String[] tags) {
        return typing(model, false, repeat, true, false, "English to Japanese Test", tags);
    }

    @GetMapping("/kanjireading")
    public String kanjiReadingGame(Model  model,
                              @RequestParam(defaultValue = "true", name = "repeat") boolean repeat,
                                   @RequestParam(defaultValue = "false") boolean hideEnglish,
                                   @RequestParam(defaultValue = "false") boolean hideKanji) {
        return typing(model, true, repeat, false, hideEnglish, "Kanji Readings Test", null);
    }

    private String typing(Model model, boolean useKanji, boolean repeat, boolean hideWord, boolean hideEnglish, String title, String[] tags) {
        // TODO Convert to String/Object map?
        if (tags != null) {
            model.addAttribute("tags", String.join(",", tags));
        }
        model.addAttribute("hideEnglish", hideEnglish);
        model.addAttribute("repeat", repeat);
        model.addAttribute("title", title);
        model.addAttribute("hideWord", hideWord);
        model.addAttribute("useKanji", useKanji);
        return "typing-page";
    }

}
