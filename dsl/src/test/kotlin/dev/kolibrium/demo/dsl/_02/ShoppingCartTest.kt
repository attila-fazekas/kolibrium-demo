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

package dev.kolibrium.demo.dsl._02

import dev.kolibrium.demo.dsl._02.Product.BACKPACK
import dev.kolibrium.demo.dsl._02.Product.BIKE_LIGHT
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class ShoppingCartTest {
    private lateinit var driver: WebDriver

    @BeforeEach
    fun setUp() {
        driver = ChromeDriver().apply {
            get("https://www.saucedemo.com")
        }
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun `shopping cart badge is updated when products added or removed`() {
        with(driver) {
            with(InventoryPage()) {
                val products = setOf(BACKPACK, BIKE_LIGHT)

                products.forEach { product ->
                    product.addToCart()
                }

                verifyShoppingCartCountIs(products.size)

                products.forEach { product ->
                    product.removeFromCart()
                }

                verifyShoppingCartIsEmpty()
            }
        }
    }
}
