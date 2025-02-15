package dev.kolibrium.demo.selenium._02

import dev.kolibrium.core.selenium.className
import org.openqa.selenium.WebDriver

class InventoryPage(driver: WebDriver) {
    private val shoppingCart by driver.className("shopping_cart_link")

    fun isShoppingCartDisplayed() = shoppingCart.isDisplayed
}
