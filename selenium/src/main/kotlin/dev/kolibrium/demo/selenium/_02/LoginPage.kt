/*
 * Copyright 2023-2024 Attila Fazekas & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.kolibrium.demo.selenium._02

import dev.kolibrium.selenium.idOrName
import dev.kolibrium.selenium.name
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