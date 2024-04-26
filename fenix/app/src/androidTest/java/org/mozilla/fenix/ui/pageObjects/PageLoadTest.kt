package org.mozilla.fenix.ui.pageObjects

import org.junit.Rule
import org.junit.Test
import org.mozilla.fenix.helpers.HomeActivityIntentTestRule

@get:Rule
val testRule = HomeActivityIntentTestRule.withDefaultSettingsOverrides(
    skipOnboarding = true,
)

class PageLoadTest: BasePage {


    @Test
    fun HomeScreenTest() {
        testRule.applySettingsExceptions {
            it.isRecentTabsFeatureEnabled = false
        }
        // Test Setup
        // No Setup Required

        // Test Step
        HomeScreenPage.navigateToPage()

        // Test Assertion
        SettingsMenuPage.navigateToPage()
    }

    fun PageLoadTest() {
        for page in pagesToTest:
        PageLoadTest(page)
    }

    fun ComponentLoadTest() {
        for component in pageToTest:

    }
}
