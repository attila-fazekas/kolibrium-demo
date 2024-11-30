package dev.kolibrium.demo.dsl._01

import dev.kolibrium.selenium.className
import org.openqa.selenium.WebDriver

context(WebDriver)
class InventoryPage {
    private val shoppingCart by className("shopping_cart_link")

    fun isShoppingCartDisplayed() = shoppingCart.isDisplayed
}
