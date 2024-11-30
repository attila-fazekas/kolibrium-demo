package dev.kolibrium.demo.dsl._03

import dev.kolibrium.dsl.selenium.creation.Arguments.Chrome.incognito
import dev.kolibrium.dsl.selenium.creation.Arguments.Chrome.start_maximized
import dev.kolibrium.dsl.selenium.creation.Switches.enable_automation
import dev.kolibrium.dsl.selenium.creation.chromeDriver
import dev.kolibrium.dsl.selenium.interactions.cookies
import dev.kolibrium.selenium.className
import dev.kolibrium.selenium.classNames
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chromium.ChromiumDriverLogLevel.INFO
import java.math.BigDecimal
import java.math.RoundingMode

class ProductsTest {
    private lateinit var driver: WebDriver

    @BeforeEach
    fun setUp() {
        driver = chromeDriver {
            driverService {
                logFile = "chrome.log"
                logLevel = INFO
                readableTimestamp = true
            }
            options {
                arguments {
                    +incognito
                    +start_maximized
                }
                experimentalOptions {
                    excludeSwitches {
                        +enable_automation
                    }
                }
            }
        }.apply {
            get("https://www.saucedemo.com")
        }
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun `there should be 6 products in total`() {
        with(driver) {
            with(ProductsPage()) {
                val products = getProducts()
                products.size shouldBe 6
            }
        }
    }

    @Test
    fun `prices should be correctly displayed`() {
        with(driver) {
            with(ProductsPage()) {
                val backpack = getProduct { it.name.text.contains("Backpack") }
                val bikeLight = getProduct { it.name.text.contains("Bike Light") }

                backpack.getPrice() shouldBe BigDecimal("29.99")
                bikeLight.getPrice() shouldBe BigDecimal("9.99")
            }
        }
    }
}

context(WebDriver)
class ProductsPage {
    private val headerContainer by className("header_container")
    private val names by classNames("inventory_item")

    init {
        cookies {
            addCookie(name = "session-username", value = "standard_user")
        }
        get("${currentUrl}inventory.html")
        check(headerContainer.isDisplayed) {
            "This is not the Inventory Page, current page is: " + this@WebDriver.currentUrl
        }
    }

    fun getProducts() = names.map { webElement ->
        Product(webElement)
    }

    fun getProduct(condition: (Product) -> Boolean): Product {
        return getProducts()
            .asSequence()
            .filter(condition) // Filter by product name or price
            .firstOrNull()
            ?: throw NoSuchElementException()
    }
}

class Product(val root: WebElement) {
    val image by root.className("inventory_item_img")
    val name by root.className("inventory_item_name")
    val description by root.className("inventory_item_desc")
    private val price by root.className("inventory_item_price")

    fun getPrice(): BigDecimal {
        return BigDecimal(price.text.replace("$", "")).setScale(
            2,
            RoundingMode.UNNECESSARY
        ) // Sanitation and formatting
    }
}
