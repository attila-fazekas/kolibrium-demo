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
    fun testEasyButtons() {
        with(driver) {
            val easyButton1 by cssSelector("#easy00")
            easyButton1.click()
            val easyButton2 by cssSelector("#easy01")
            easyButton2.click()
            val easyButton3 by cssSelector("#easy02")
            easyButton3.click()
            val easyButton4 by cssSelector("#easy03")
            easyButton4.click()
            val easyMessage by id("easybuttonmessage")

            easyMessage.text shouldBe "All Buttons Clicked"
        }
    }

    @Test
    fun testEasyButtons_pageObject() {
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
    fun testHardButtons() {
        with(driver) {
            val hardButton1 by cssSelector("#button00") {
                until = { isEnabled }
            }
            hardButton1.click()

            val hardButton2 by cssSelector("#button01") {
                until = { isEnabled }
            }
            hardButton2.click()

            val hardButton3 by cssSelector("#button02") {
                until = { isEnabled }
            }
            hardButton3.click()

            val hardButton4 by cssSelector("#button03") {
                until = { isEnabled }
            }
            hardButton4.click()

            val hardMessage by id("buttonmessage")

            hardMessage.text shouldBe "All Buttons Clicked"
        }
    }

    @Test
    fun testHardButtons_pageObject() {
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
