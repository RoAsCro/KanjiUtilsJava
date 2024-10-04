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
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
                              @RequestParam(defaultValue = "true", name = "repeat") boolean repeat) {
        return typing(model, repeat, false, "Readings Test");
    }

    @GetMapping("/wordgame")
    public String wordGame(Model  model,
                           @RequestParam(defaultValue = "true", name = "repeat") boolean repeat) {
        return typing(model, repeat, true, "English to Japanese Test");
    }

    private String typing(Model model, boolean repeat, boolean showWord, String title) {
        model.addAttribute("repeat", repeat);
        model.addAttribute("title", title);
        model.addAttribute("showWord", showWord);
        return "typing-page";
    }

}
