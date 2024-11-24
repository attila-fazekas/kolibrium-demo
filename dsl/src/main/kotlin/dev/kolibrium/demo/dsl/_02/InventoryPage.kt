package dev.kolibrium.demo.dsl._02

import dev.kolibrium.dsl.selenium.interactions.cookies
import dev.kolibrium.selenium.*
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

context(WebDriver)
class InventoryPage {
    private val shoppingCartBadge by cssSelectors("span[data-test='shopping-cart-badge']", cacheLookup = false)
    private val sortMenu by cssSelector("select[data-test='product-sort-container']")
    private val products by xPaths("//*[@data-test='inventory-item']")

    init {
        cookies {
            addCookie(name = "session-username", value = "standard_user")
        }
        get("${currentUrl}inventory.html")
        check(sortMenu.isDisplayed) {
            "This is not the Inventory Page, current page is: " + this@WebDriver.currentUrl
        }
    }

    fun Product.addToCart() {
        products.forEach { webElement ->
            val item = Item(webElement, this)
            if (item.name.text.contains(productName)) {
                item.addToCartButton.click()
            }
        }
    }

    fun Product.removeFromCart() {
        products.forEach { webElement ->
            val item = Item(webElement, this)
            if (item.name.text.contains(productName)) {
                item.removeFromCartButton.click()
            }
        }
    }

    fun verifyShoppingCartCountIs(count: Int) {
        shoppingCartBadge.first().text shouldBe count.toString()
    }

    fun verifyShoppingCartIsEmpty() {
        shoppingCartBadge.shouldBeEmpty()
    }
}

class Item(root: WebElement, product: Product) {
    val image by root.className("inventory_item_img")
    val name by root.className("inventory_item_name")
    val description by root.className("inventory_item_desc")
    val price by root.className("inventory_item_price")
    val addToCartButton by root.id("add-to-cart-sauce-labs-${product.locatorName}")
    val removeFromCartButton by root.id("remove-sauce-labs-${product.locatorName}")
}
