package self.roashe.kanutils.backend.dao;

import self.roashe.kanutils.backend.model.Word;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.List;

public class ImportDaoImplKanshudoImpl implements ImportDao {



    @Override
    public List<Word> getAllWords(String username, String password) {
        return List.of();
    }


}
