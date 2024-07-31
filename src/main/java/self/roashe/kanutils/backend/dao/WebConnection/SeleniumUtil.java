package self.roashe.kanutils.backend.dao.WebConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class SeleniumUtil {

    private static final String DASHBOARD_TITLE = "Dashboard - Kanshudo";

    private static final String ELEM_USERNAME = "user_email";
    private static final String ELEM_PASSWORD = "user_password";
    private static final String ELEM_DETAILS = "details";

    private static final String HREF = "href";

    private static final String URL_KANSHUDO = "https://www.kanshudo.com";
    private static final String URL_LOGIN = "/users/sign_in";
    private static final String URL_FLASHCARDS = "/srs";



    public static void main(String[] args) {
        getFlashcards(args[0], args[1], args[3]);
    }

    public static String getFlashcards(String username, String password, String geckoLocation) {

        StringBuilder vocab = new StringBuilder();

        FirefoxOptions options = new FirefoxOptions();
        System.setProperty("webdriver.gecko.driver",
                geckoLocation);
        options.addArguments("-headless");

        WebDriver driver = new FirefoxDriver(options);
//        WebDriver driver = new HtmlUnitDriver();

        // /users/sign_in
        driver.get(URL_KANSHUDO + URL_LOGIN);

        // Get login form elements
        WebElement uNameField = driver.findElement(By.id(ELEM_USERNAME));
        WebElement pField = driver.findElement(By.id(ELEM_PASSWORD));
        // Enter login details and sign in
        uNameField.sendKeys(username);
        pField.sendKeys(password);
        uNameField.submit();



        // /dashboard -> /srs
        Wait<WebDriver> waitForLogin = new WebDriverWait(driver, 5);
        waitForLogin.until(d -> d.getTitle().equals(DASHBOARD_TITLE));

        driver.get(URL_KANSHUDO + URL_FLASHCARDS);
        List<String> flashcardLinks = driver.findElements(By.className(ELEM_DETAILS))
                .stream().map(anchor -> anchor.getAttribute(HREF))
                .toList();

        for (String url : flashcardLinks) {
            // /srs/details?srs=[flashcard set reference]
            driver.get(url);

            WebElement dlButton = driver.findElement(By.cssSelector("#download_set"));
            dlButton.click();

            Wait<WebDriver> waitForText = new WebDriverWait(driver, 5);
            waitForText.until(d -> !d.findElement(By.id("dcontent")).getAttribute("value").isEmpty());
            vocab.append(driver.findElement(By.id("dcontent")).getAttribute("value"));

        }
        driver.quit();
        return vocab.toString();
    }
}
