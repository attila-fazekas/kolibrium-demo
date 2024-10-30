package dev.kolibrium.demo.ksp._01

import dev.kolibrium.demo.ksp._01.pages.InventoryPage
import dev.kolibrium.demo.ksp._01.pages.LoginPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class KspTest {
    private lateinit var driver: WebDriver

    @BeforeEach
    fun setUp() {
        driver = ChromeDriver()
        driver["https://www.saucedemo.com/"]
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun loginTest() {
        with(driver) {
            LoginPage().login()

            InventoryPage().isShoppingCartDisplayed() shouldBe true
        }
    }
}