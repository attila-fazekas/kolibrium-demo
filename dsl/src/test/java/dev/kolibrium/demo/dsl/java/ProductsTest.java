package dev.kolibrium.demo.dsl.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.chromium.ChromiumDriverLogLevel.INFO;

// example from https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/#page-component-objects

public class ProductsTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        var driverService = new ChromeDriverService.Builder()
                .withLogFile(new File("chrome.log"))
                .withLogLevel(INFO)
                .withReadableTimestamp(true)
                .build();

        var options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches",
                List.of("enable-automation"));

        driver = new ChromeDriver(driverService, options);
        driver.get("https://www.saucedemo.com");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("there should be 6 products in total")
    public void testProductInventory() {
        var productsPage = new ProductsPage(driver);
        var products = productsPage.getProducts();
        assertThat(products.size()).isEqualTo(6);
    }
    
    @Test
    @DisplayName("prices should be correctly displayed")
    public void testProductPrices() {
        var productsPage = new ProductsPage(driver);

        // Pass a lambda expression (predicate) to filter the list of products
        // The predicate or "strategy" is the behavior passed as parameter
        var backpack = productsPage.getProduct(p -> p.getName().contains("Backpack")); // page component object
        var bikeLight = productsPage.getProduct(p -> p.getName().contains("Bike Light"));

        assertThat(backpack.getPrice()).isEqualTo(new BigDecimal("29.99"));
        assertThat(bikeLight.getPrice()).isEqualTo(new BigDecimal("9.99"));
    }
}

class ProductsPage {
    private final WebDriver driver;

    private final By headerContainer = By.className("header_container");
    private final By name = By.className("inventory_item");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        var cookie = new Cookie("session-username", "standard_user");
        driver.manage().addCookie(cookie);
        driver.get("https://www.saucedemo.com/inventory.html");

        // No assertions, throws an exception if the element is not loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> d.findElement(headerContainer));
    }

    // Returning a list of products is a service of the page
    public List<Product> getProducts() {
        return driver.findElements(name)
                .stream()
                .map(Product::new) // Map WebElement to a product component
                .toList();
    }

    // Return a specific product using a boolean-valued function (predicate)
    // This is the behavioral Strategy Pattern from GoF
    public Product getProduct(Predicate<Product> condition) {
        return getProducts()
                .stream()
                .filter(condition) // Filter by product name or price
                .findFirst()
                .orElseThrow();
    }
}

class Product {
    private final WebElement root;

    private final By name = By.className("inventory_item_name");
    private final By price = By.className("inventory_item_price");
    private final By id = By.id("add-to-cart-sauce-labs-");

    // The root element contains the entire component
    public Product(WebElement root) {
        this.root = root;
    }

    public String getName() {
        // Locating an element begins at the root of the component
        return root.findElement(name).getText();
    }

    public BigDecimal getPrice() {
        return new BigDecimal(
                root.findElement(price)
                        .getText()
                        .replace("$", "")
        ).setScale(2, RoundingMode.UNNECESSARY); // Sanitation and formatting
    }

    public void addToCart() {
        root.findElement(id).click();
    }
}