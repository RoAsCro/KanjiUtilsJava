package self.roashe.kanutils.backend.controller.pagescontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import self.roashe.kanutils.backend.service.VocabService;

import java.io.File;

@Controller
@RequestMapping("/words")
public class WordJSPController  {

    @Autowired
    private VocabService service;

    @GetMapping("/viewWords")
    public String viewWords(Model model) {
        System.out.printf("Here");
        model.addAttribute("vocab", this.service.getAllVocab());
        return "view-words";
    }

}
