package dev.kolibrium.demo.ksp._01.pages

import dev.kolibrium.demo.ksp._01.locators.generated.InventoryPageLocators
import org.openqa.selenium.WebDriver

context(WebDriver)
class InventoryPage {
    private val locators = InventoryPageLocators()

    fun isShoppingCartDisplayed() = locators.shoppingCart.isDisplayed
}