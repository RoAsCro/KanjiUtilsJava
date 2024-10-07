package self.roashe.kanutils.backend.controller.pagescontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import self.roashe.kanutils.backend.service.VocabService;

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
                              @RequestParam(defaultValue = "true") boolean reverseKana) {
        return typing(model, false, repeat, false, hideEnglish, reverseKana,
                "Readings Test", tags);
    }

//    @GetMapping("/flashcard")
//    public String flashcards(Model model,
//                             @RequestParam(defaultValue = "true", name = "repeat") boolean repeat) {
//        return typing(model, false, repeat, false, true, )
//    }

    @GetMapping("/wordgame")
    public String wordGame(Model  model,
                           @RequestParam(defaultValue = "true", name = "repeat") boolean repeat,
                           @RequestParam(required = false) String[] tags) {
        return typing(model, false, repeat, true, true,
                false, "English to Japanese Test", tags);
    }

    @GetMapping("/kanjireading")
    public String kanjiReadingGame(Model  model,
                              @RequestParam(defaultValue = "true", name = "repeat") boolean repeat,
                                   @RequestParam(defaultValue = "false") boolean hideEnglish) {
        return typing(model, true, repeat, false, hideEnglish, true,
                "Kanji Readings Test", null);
    }

    private String typing(Model model, boolean useKanji, boolean repeat,
                          boolean hideWord, boolean hideEnglish, boolean reverseKana,
                          String title, String[] tags) {
        // TODO Convert to String/Object map?
        if (tags != null) {
            model.addAttribute("tags", String.join(",", tags));
        }
        model.addAttribute("hideEnglish", hideEnglish);
        model.addAttribute("repeat", repeat);
        model.addAttribute("title", title);
        model.addAttribute("hideWord", hideWord);
        model.addAttribute("useKanji", useKanji);
        model.addAttribute("reverseKana", reverseKana);

        return "typing-page";
    }

}
