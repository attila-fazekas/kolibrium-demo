package dev.kolibrium.demo.selenium._01

import dev.kolibrium.selenium.className
import dev.kolibrium.selenium.id
import dev.kolibrium.selenium.name
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class DelegatesTest {
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
            val username by name("user-name")
            val password by id("password")
            val button by name("login-button")

            username.sendKeys("standard_user")
            password.sendKeys("secret_sauce")
            button.click()

            val shoppingCart by className("shopping_cart_link")

            shoppingCart.isDisplayed shouldBe true
        }
    }
}
