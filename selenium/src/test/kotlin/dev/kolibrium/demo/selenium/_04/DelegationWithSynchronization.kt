package dev.kolibrium.demo.selenium._04

import dev.kolibrium.selenium.cssSelector
import dev.kolibrium.selenium.id
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class DelegationWithSynchronization {
    private lateinit var driver: WebDriver

    @BeforeEach
    fun setUp() {
        driver = ChromeDriver().apply {
            get("https://eviltester.github.io/synchole/buttons.html")
        }
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun `easy buttons clicked`() {
        with(driver) {
            with(ButtonsPage()) {
                easyButton1.click()
                easyButton2.click()
                easyButton3.click()
                easyButton4.click()

                easyMessage.text shouldBe "All Buttons Clicked"
            }
        }
    }

    @Test
    fun `hard buttons clicked`() {
        with(driver) {
            with(ButtonsPage()) {
                hardButton1.click()
                hardButton2.click()
                hardButton3.click()
                hardButton4.click()

                hardMessage.text shouldBe "All Buttons Clicked"
            }
        }
    }
}

context(WebDriver)
class ButtonsPage {
    val easyButton1 by cssSelector("#easy00")
    val easyButton2 by cssSelector("#easy01")
    val easyButton3 by cssSelector("#easy02")
    val easyButton4 by cssSelector("#easy03")
    val easyMessage by id("easybuttonmessage")

    val hardButton1 by cssSelector("#button00") { isEnabled }
    val hardButton2 by cssSelector("#button01") { isEnabled }
    val hardButton3 by cssSelector("#button02") { isEnabled }
    val hardButton4 by cssSelector("#button03") { isEnabled }
    val hardMessage by id("buttonmessage")
}
