package dev.kolibrium.demo.selenium

import com.google.auto.service.AutoService
import dev.kolibrium.common.WebElements
import dev.kolibrium.core.selenium.Wait.Companion.DEFAULT
import dev.kolibrium.core.selenium.configuration.AbstractSeleniumProjectConfiguration
import dev.kolibrium.core.selenium.decorators.BorderStyle.DASHED
import dev.kolibrium.core.selenium.decorators.Color.GREEN
import dev.kolibrium.core.selenium.decorators.ElementStateCacheDecorator
import dev.kolibrium.core.selenium.decorators.HighlighterDecorator
import dev.kolibrium.core.selenium.decorators.SlowMotionDecorator
import dev.kolibrium.core.selenium.isClickable
import org.openqa.selenium.WebElement
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@AutoService(AbstractSeleniumProjectConfiguration::class)
object SeleniumConfiguration : AbstractSeleniumProjectConfiguration() {
    override val elementReadyCondition: (WebElement.() -> Boolean) = { isClickable }

    override val elementsReadyCondition: (WebElements.() -> Boolean) = { isClickable }

    override val decorators = listOf(
        HighlighterDecorator(style = DASHED, color = GREEN, width = 5),
        SlowMotionDecorator(wait = 500.milliseconds),
        ElementStateCacheDecorator()
    )

    override val waitConfig = DEFAULT.copy(
        pollingInterval = 250.milliseconds,
        timeout = 7.seconds,
    )
}
