package dev.kolibrium.demo.selenium

import com.google.auto.service.AutoService
import dev.kolibrium.selenium.Wait.Companion.DEFAULT
import dev.kolibrium.selenium.configuration.AbstractSeleniumProjectConfiguration
import dev.kolibrium.selenium.decorators.BorderStyle.DOTTED
import dev.kolibrium.selenium.decorators.Color.BLUE
import dev.kolibrium.selenium.decorators.HighlighterDecorator
import dev.kolibrium.selenium.decorators.SlowMotionDecorator
import dev.kolibrium.selenium.isClickable
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@AutoService(AbstractSeleniumProjectConfiguration::class)
object SeleniumConfiguration : AbstractSeleniumProjectConfiguration() {
    override val elementReadyWhen: (WebElement.() -> Boolean) = { isClickable }

    override val decorators = listOf(
        { ctx: SearchContext ->
            HighlighterDecorator
                .configure(style = DOTTED, color = BLUE, width = 10)
                .decorate(ctx)
        },
        { ctx: SearchContext ->
            SlowMotionDecorator
                .configure(wait = 500.milliseconds)
                .decorate(ctx)
        }
    )

    override var wait = DEFAULT.copy(timeout = 8.seconds)
}
