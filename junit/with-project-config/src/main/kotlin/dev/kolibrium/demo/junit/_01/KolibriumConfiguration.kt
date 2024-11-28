package dev.kolibrium.demo.junit._01

import com.google.auto.service.AutoService
import dev.kolibrium.dsl.selenium.creation.Arguments.Chrome.incognito
import dev.kolibrium.dsl.selenium.creation.Arguments.Chrome.start_maximized
import dev.kolibrium.dsl.selenium.creation.ExperimentalFlags.cookies_without_same_site_must_be_secure
import dev.kolibrium.dsl.selenium.creation.ExperimentalFlags.same_site_by_default_cookies
import dev.kolibrium.dsl.selenium.creation.Switches.enable_automation
import dev.kolibrium.dsl.selenium.creation.chromeDriver
import dev.kolibrium.junit.configuration.AbstractJUnitProjectConfiguration
import dev.kolibrium.selenium.Wait
import dev.kolibrium.selenium.configuration.AbstractSeleniumProjectConfiguration
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.StaleElementReferenceException
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@AutoService(AbstractJUnitProjectConfiguration::class)
object JUnitConfiguration : AbstractJUnitProjectConfiguration() {
    override val baseUrl = "https://www.saucedemo.com"

    override val chromeDriver = {
        chromeDriver {
            options {
                arguments {
                    +incognito
                    +start_maximized
                }
                experimentalOptions {
                    excludeSwitches {
                        +enable_automation
                    }
                    localState {
                        browserEnabledLabsExperiments {
                            +same_site_by_default_cookies
                            +cookies_without_same_site_must_be_secure
                        }
                    }
                }
            }
        }
    }
}

@AutoService(AbstractSeleniumProjectConfiguration::class)
object SeleniumConfiguration : AbstractSeleniumProjectConfiguration() {
    override val wait = Wait(
        pollingInterval = 200.milliseconds,
        timeout = 10.seconds,
        message = "Element could not be found",
        ignoring = arrayOf(NoSuchElementException::class, StaleElementReferenceException::class),
    )
}
