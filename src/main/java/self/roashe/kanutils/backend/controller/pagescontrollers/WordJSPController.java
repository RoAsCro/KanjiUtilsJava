package self.roashe.kanutils.backend.controller.pagescontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String readingGame(Model  model) {
        model.addAttribute("title", "Readings Test");
        model.addAttribute("showWord", false);
        return "typing-page";
    }

    @GetMapping("/wordgame")
    public String wordGame(Model  model) {
        model.addAttribute("title", "English to Japanese Test");
        model.addAttribute("showWord", true);
        return "typing-page";
    }

    @GetMapping("/viewWord")
    public String viewWord(Model model) {
        List<Word> words = this.service.getAllVocab();
        Random random = new Random();
        int randomNum  = random.nextInt(words.size() + 1);
        model.addAttribute("vocab", words.subList(randomNum, randomNum + 1));
        ;
        return "view-words";
    }

    @GetMapping("/do")
    public void doThing(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Weee");
    }

}
