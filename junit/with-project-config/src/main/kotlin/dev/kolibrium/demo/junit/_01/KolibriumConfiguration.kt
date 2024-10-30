package dev.kolibrium.demo.junit._01

import com.google.auto.service.AutoService
import dev.kolibrium.core.Browser
import dev.kolibrium.dsl.selenium.creation.Arguments.Chrome.incognito
import dev.kolibrium.dsl.selenium.creation.Arguments.Chrome.start_maximized
import dev.kolibrium.dsl.selenium.creation.ExperimentalFlags
import dev.kolibrium.dsl.selenium.creation.ExperimentalFlags.cookies_without_same_site_must_be_secure
import dev.kolibrium.dsl.selenium.creation.ExperimentalFlags.same_site_by_default_cookies
import dev.kolibrium.dsl.selenium.creation.Switches
import dev.kolibrium.dsl.selenium.creation.Switches.enable_automation
import dev.kolibrium.dsl.selenium.creation.chromeDriver
import dev.kolibrium.junit.config.AbstractProjectConfiguration

@AutoService(AbstractProjectConfiguration::class)
class KolibriumConfiguration : AbstractProjectConfiguration() {
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