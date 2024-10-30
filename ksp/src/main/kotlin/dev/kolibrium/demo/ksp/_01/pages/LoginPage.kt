package dev.kolibrium.demo.ksp._01.pages

import dev.kolibrium.demo.ksp._01.locators.generated.LoginPageLocators
import org.openqa.selenium.WebDriver

context(WebDriver)
class LoginPage {
    private val locators = LoginPageLocators()

    fun login(username: String = "standard_user", password: String = "secret_sauce") = with(locators) {
        this.username.sendKeys(username)
        this.password.sendKeys(password)
        loginButton.submit()
    }
}