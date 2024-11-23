package dev.kolibrium.demo.junit._01

import dev.kolibrium.demo.junit._01.pages.InventoryPage
import dev.kolibrium.demo.junit._01.pages.LoginPage
import dev.kolibrium.junit.Kolibrium
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver

context(WebDriver)
@Kolibrium
class JUnitTest {
    @Test
    fun loginTest() {
        LoginPage().login(
            username = "standard_user",
            password = "secret_sauce"
        )

        InventoryPage().isShoppingCartDisplayed() shouldBe true
    }
}