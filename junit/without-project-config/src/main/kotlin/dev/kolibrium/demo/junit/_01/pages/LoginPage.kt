package dev.kolibrium.demo.junit._01.pages

import dev.kolibrium.core.selenium.idOrName
import dev.kolibrium.core.selenium.name
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

context(WebDriver)
class LoginPage {
    private val username: WebElement by name("user-name")

    private val password: WebElement by idOrName("password")

    private val button: WebElement by name("login-button")

    fun login(username: String, password: String) {
        this.username.sendKeys(username)
        this.password.sendKeys(password)
        button.click()
    }
}
