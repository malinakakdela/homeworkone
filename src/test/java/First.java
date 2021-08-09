import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class First {

    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode='primary']");
    private final By FIRST_LINK = By.xpath(".//*[contains(@class, 'list-article__headline')]");
    private final By COMMENTS_LINK = By.xpath(".//*[contains(@class, 'article-share__item--comments')]");
    private final Logger LOGGER = LogManager.getLogger(First.class);
    private final By COMMENT_COUNT = By.xpath(".//*[contains(@class, 'list-article__comment section-font-color')]");
    private final By ARTICLES = By.xpath(".//span[contains(@class, 'list-article__headline')]");

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        LOGGER.info("Opening window");
        driver.manage().window().maximize();
        driver.get("https://tvnet.lv");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ACCEPT_COOKIE_BTN));
        WebElement cookie = driver.findElement(ACCEPT_COOKIE_BTN);
        cookie.click();

        List <WebElement> commentCount = driver.findElements(COMMENT_COUNT);
        for (int i = 0;i <5;i++){
            if (!commentCount.get(i).getText().isEmpty()){
                System.out.println(i + commentCount.get(i).getText());
            }
        }

        List <WebElement> articles = driver.findElements(ARTICLES);
        for (int i = 0;     i < 5; i++){
            if (!articles.get(i).getText().isEmpty()){
                System.out.println(i + articles.get(i).getText());
            }
        }

        LOGGER.info("Opening first page");
        WebElement link = driver.findElement(FIRST_LINK);
        List<WebElement> webArticles = driver.findElements(FIRST_LINK);
        Assertions.assertTrue(!webArticles.isEmpty(), "There is no article");
        link.click();

        LOGGER.info("Opening commentaries");
        WebElement comments = driver.findElement(COMMENTS_LINK);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(COMMENTS_LINK));
        comments.click();

        String title = driver.getTitle();
        System.out.println(title);
    }

}
