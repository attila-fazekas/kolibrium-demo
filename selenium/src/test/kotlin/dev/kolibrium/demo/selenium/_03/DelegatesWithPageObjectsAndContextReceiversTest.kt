package dev.kolibrium.demo.selenium._03

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class DelegatesWithPageObjectsAndContextReceiversTest {
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
            LoginPage().login(
                username = "standard_user",
                password = "secret_sauce"
            )

            InventoryPage().isShoppingCartDisplayed() shouldBe true
        }
    }
}
