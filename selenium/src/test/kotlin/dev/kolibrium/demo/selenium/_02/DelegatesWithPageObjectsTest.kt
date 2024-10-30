package dev.kolibrium.demo.selenium._02

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class DelegatesWithPageObjectsTest {
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
        LoginPage(driver).login(
            username = "standard_user",
            password = "secret_sauce"
        )

        InventoryPage(driver).isShoppingCartDisplayed() shouldBe true
    }
}
