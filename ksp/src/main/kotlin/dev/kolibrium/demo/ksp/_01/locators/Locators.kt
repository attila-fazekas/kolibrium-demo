package dev.kolibrium.demo.ksp._01.locators

import dev.kolibrium.ksp.annotations.ClassName
import dev.kolibrium.ksp.annotations.Id
import dev.kolibrium.ksp.annotations.Locators
import dev.kolibrium.ksp.annotations.Name

@Locators
enum class LoginPageLocators {
    @Id("user-name")
    username,

    password,

    @Name("login-button")
    loginButton
}

@Locators
enum class InventoryPageLocators {
    @ClassName("shopping_cart_link")
    shoppingCart
}
