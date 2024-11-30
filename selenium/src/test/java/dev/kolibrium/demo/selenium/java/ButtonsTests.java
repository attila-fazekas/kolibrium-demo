package dev.kolibrium.demo.selenium.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class ButtonsTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://eviltester.github.io/synchole/buttons.html");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testEasyButtons() {
        var buttonsPage = new ButtonsPage(driver);

        buttonsPage.waitAndClick(buttonsPage.easyButton1, ExpectedConditions::visibilityOfElementLocated);
        buttonsPage.waitAndClick(buttonsPage.easyButton2, ExpectedConditions::visibilityOfElementLocated);
        buttonsPage.waitAndClick(buttonsPage.easyButton3, ExpectedConditions::visibilityOfElementLocated);
        buttonsPage.waitAndClick(buttonsPage.easyButton4, ExpectedConditions::visibilityOfElementLocated);

        assertThat(buttonsPage.getEasyMessage()).isEqualTo("All Buttons Clicked");
    }

    @Test
    public void testHardButtons() {
        var buttonsPage = new ButtonsPage(driver);

        buttonsPage.waitAndClick(buttonsPage.hardButton1, ExpectedConditions::elementToBeClickable);
        buttonsPage.waitAndClick(buttonsPage.hardButton2, ExpectedConditions::elementToBeClickable);
        buttonsPage.waitAndClick(buttonsPage.hardButton3, ExpectedConditions::elementToBeClickable);
        buttonsPage.waitAndClick(buttonsPage.hardButton4, ExpectedConditions::elementToBeClickable);

        assertThat(buttonsPage.getHardMessage()).isEqualTo("All Buttons Clicked");
    }
}

class ButtonsPage {
    private final WebDriver driver;

    final By easyButton1 = cssSelector("#easy00");
    final By easyButton2 = cssSelector("#easy01");
    final By easyButton3 = cssSelector("#easy02");
    final By easyButton4 = cssSelector("#easy03");
    final By easyMessage = id("easybuttonmessage");

    final By hardButton1 = cssSelector("#button00");
    final By hardButton2 = cssSelector("#button01");
    final By hardButton3 = cssSelector("#button02");
    final By hardButton4 = cssSelector("#button03");
    final By hardMessage = id("buttonmessage");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitAndClick(By by, Function<By, ExpectedCondition<WebElement>> condition) {
        var button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(condition.apply(by));
        button.click();
    }

    public String getEasyMessage() {
        return driver.findElement(easyMessage).getText();
    }

    public String getHardMessage() {
        return driver.findElement(hardMessage).getText();
    }
}
