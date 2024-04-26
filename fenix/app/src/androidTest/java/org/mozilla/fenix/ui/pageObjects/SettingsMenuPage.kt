package org.mozilla.fenix.ui.pageObjects

import org.mozilla.fenix.ui.robots.homeScreen

class SettingsMenuPage: BasePage() {
    //nav use cases:
    //1.specific user journey: we care ab a specific nav path
    //-write custom key for nav path

    //2.just functionality test: we don't care at all how we get to the page
    // - use a deeplink (if exists)

    //3. non-specific journey: we don't care about the specific path, only that we've navigated to the page
    //-implement a 'default' path to the page
    navigationPath {
        "From":
            "default":
                "MainMenuPage(state)": {
                    "mozClick(settingsButton)"
                }
            "custom": ["PageObject.action1()", "action2"...]
            "deepLink": "://settings"


        "To":
            "privacySettings": "privacyMenu.click()"
    }

    data object NavigationPath {
        val fromDefault: Map<String, Any> = mapOf("MainMenuPage" to mozClick(settingsButton))
        val fromCustom: Map<String, Any> = mapOf("SecuritySheetPage" to mozClick(settingsButton), "fromCustom2" to action2())
        val fromDeepLink: String = "://settings"
    }

    elements {
        generalSettingsHeading: {
            selector: "General"
            strategy: "Espresso",
            groups: {
            // this is only for elements within the Viewport when loading page
            // TODO: figure out how we want to handle required elements NOT in viewport,
            //     like scrolling to elements
                requiredForPage: true
        }
        }

        searchSettingButton: {
            selector: "R.string.preferences_search"
            strategy: "Espresso"
            groups: {
            // this is only for elements within the Viewport when loading page
            // TODO: figure out how we want to handle required elements NOT in viewport,
            //     like scrolling to elements
                requiredForPage: true
        }
        }

        tabsSettingButton: {
            selector: "getStringResource(R.string.preferences_tabs)"
            strategy: ["UIAutomator", "itemContainingText"]
            groups: {
            // this is only for elements within the Viewport when loading page
            // TODO: figure out how we want to handle required elements NOT in viewport,
            //     like scrolling to elements
                requiredForPage: true
        }
        }

        homepageSettingButton: {
            selector: "R.string.preferences_home_2"
            strategy: "Espresso"
            groups: {
            // this is only for elements within the Viewport when loading page
            // TODO: figure out how we want to handle required elements NOT in viewport,
            //     like scrolling to elements
                requiredForPage: true
        }
        }

        homepageSettingButton: {
            selector: "Customize"
            strategy: "Espresso"
            groups: {
            // this is only for elements within the Viewport when loading page
            // TODO: figure out how we want to handle required elements NOT in viewport,
            //     like scrolling to elements
                requiredForPage: true
        }
        }
    }


    // Test Steps



    }
}
