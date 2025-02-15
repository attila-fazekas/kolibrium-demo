package dev.kolibrium.demo.selenium._02

import dev.kolibrium.core.selenium.idOrName
import dev.kolibrium.core.selenium.name
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class LoginPage(driver: WebDriver) {
    private val username: WebElement by driver.name("user-name")

    private val password: WebElement by driver.idOrName("password")

    private val button: WebElement by driver.name("login-button")

    fun login(username: String, password: String) {
        this.username.sendKeys(username)
        this.password.sendKeys(password)
        button.click()
    }
}
