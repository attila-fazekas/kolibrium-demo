package dev.kolibrium.demo.dsl._01

import dev.kolibrium.dsl.selenium.creation.Arguments.Chrome.incognito
import dev.kolibrium.dsl.selenium.creation.chromeDriver
import dev.kolibrium.selenium.className
import dev.kolibrium.selenium.id
import dev.kolibrium.selenium.name
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver

class DslTest {
    private lateinit var driver: WebDriver

    @BeforeEach
    fun setUp() {
        driver = chromeDriver {
            driverService {
                appendLog = true
                readableTimestamp = true
            }
            options {
                arguments {
                    +incognito
                    windowSize {
                        width = 1920
                        height = 1080
                    }
                }
            }
        }

        driver["https://www.saucedemo.com/"]
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun loginTest() {
        with(driver) {
            LoginPage().login(username = "visual_user")

            InventoryPage().isShoppingCartDisplayed() shouldBe true
        }
    }
}