package self.roashe.kanutils.backend.dao.web_utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        getFlashcards(args[0], args[1], args[2]);
    }

    public static String getFlashcards(String username, String password, String geckoLocation) {

        StringBuilder vocab = new StringBuilder();

        FirefoxOptions options = new FirefoxOptions();
        System.setProperty("webdriver.gecko.driver",
                geckoLocation);
        options.addArguments("-headless");

        WebDriver driver = new FirefoxDriver(options);

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
        Wait<WebDriver> waitForLogin = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        waitForLogin.until(d -> d.getTitle().equals(DASHBOARD_TITLE));

        driver.get(URL_KANSHUDO + URL_FLASHCARDS);
        List<String> flashcardLinks = driver.findElements(By.className(ELEM_DETAILS))
                .stream().map(anchor -> anchor.getAttribute(HREF))
                .collect(Collectors.toList());
        int count = 0;
        for (String url : flashcardLinks) {
            count++;
            System.out.println("Getting set " + count + "...");
            // /srs/details?srs=[flashcard set reference]
            driver.get(url);

            WebElement dlButton = driver.findElement(By.cssSelector("#download_set"));
            dlButton.click();

            Wait<WebDriver> waitForText = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
            waitForText.until(d -> !d.findElement(By.id("dcontent")).getAttribute("value").isEmpty());
            String wordList = driver.findElement(By.id("dcontent")).getAttribute("value");
            String setName = driver.findElement(By.cssSelector("#currentVal_Title")).getText();
            String toAdd = Arrays.stream(wordList.split("\n"))
                    .map(s -> s + " TAGS<" + setName + ">")
                    .collect(Collectors.joining("\n"));
            vocab.append(toAdd).append("\n");

        }
        driver.quit();
        return vocab.toString();
    }
}
