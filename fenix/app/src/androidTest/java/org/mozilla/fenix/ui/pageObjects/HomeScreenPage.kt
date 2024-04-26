package org.mozilla.fenix.ui.pageObjects

class HomeScreenPage {
    // navigation use cases:
    // specific user-journey:
    // we care about a specific navigation path
    // solution: write custom key for navigation path

    // non-specific user-journey:
    // we don't care about the specific path, only that we've navigated there
    // solution: implement 'default' path

    // UI Functionality Test
    // we don't care at all how we get to a page
    // solution: use a deeplink if it exists, otherwise use default path


    navigationPath {
        From: {
            default: {
            HomeScreenPage: "TestRule: skipOnboarding = true",
        },
        },
        To: {
            SettingsMenuDropdown: [ "settingsMenuDropdown", "mozClick" ]
        }
    }

   val elements =
       [
           {
               "homepageWordmark": {
                   "selector": "$packageName:id/wordmark",
                   "strategy": ["UIAutomator", "itemWithResId"],
                   "groups": [{ requiredForPage: true }]
                }
           },
           {
               "privateBrowsingButton": {
                   "selector": "$packageName:id/privateBrowsingButton",
                   "strategy": ["UIAutomator", "itemWithResId"],
                   "groups": [{ "requiredForPage": true }]
                }
            },
           {
               "navigationToolbar": {
                   "selector": "$packageName:id/toolbar",
                   "strategy": ["UIAutomator", "itemWithResId"],
                   "groups": [{"requiredForPage": true}],
            }
        },
       ]

    // Test Steps
}
