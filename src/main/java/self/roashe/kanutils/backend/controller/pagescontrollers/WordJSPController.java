package self.roashe.kanutils.backend.controller.pagescontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import self.roashe.kanutils.backend.service.VocabService;

import java.util.Arrays;
import java.util.Map;
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
                              @RequestParam(defaultValue = "true") boolean reverseKana) {
        return typing(model, false, repeat, false, hideEnglish, reverseKana,
                "Readings Test", tags);

    }

    @GetMapping("/flashcard")
    public String flashcards(Model model,
                             @RequestParam(defaultValue = "true", name = "repeat") boolean repeat,
                             @RequestParam(required = false) String[] tags,
                             @RequestParam(defaultValue = "false") boolean hideEnglish,
                             @RequestParam(defaultValue = "true") boolean reverseKana) {
        model.addAttribute("attributes",
                new AttributesMap()
                        .setRepeat(repeat)
                        .setTags(String.join(",", tags))
                        .setHideEnglish(hideEnglish)
                        .setReverseKana(reverseKana));
        return "typing-page-two";
    }

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

    public class AttributesMap {
        private boolean hideEnglish =  false;
        private boolean hideWord =  false;
        private boolean repeat =  true;
        private boolean reverseKana =  true;
        private boolean useKanji = false;
        private String tags = "";
        private String title = "KanUtils";


        public boolean getHideEnglish() {
            return hideEnglish;
        }

        public AttributesMap setHideEnglish(boolean hideEnglish) {
            this.hideEnglish = hideEnglish;
            return this;
        }

        public boolean getHideWord() {
            return hideWord;
        }

        public AttributesMap setHideWord(boolean hideWord) {
            this.hideWord = hideWord;
            return this;
        }

        public boolean getRepeat() {
            return repeat;
        }

        public AttributesMap setRepeat(boolean repeat) {
            this.repeat = repeat;
            return this;
        }

        public boolean getReverseKana() {
            return reverseKana;
        }

        public AttributesMap setReverseKana(boolean reverseKana) {
            this.reverseKana = reverseKana;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public AttributesMap setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getTags() {
            return tags;
        }

        public AttributesMap setTags(String tags) {
            this.tags = tags;
            return this;
        }

        public boolean getUseKanji() {
            return useKanji;
        }

        public void setUseKanji(boolean useKanji) {
            this.useKanji = useKanji;
        }
    }

}
