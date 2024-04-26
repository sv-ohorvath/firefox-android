package org.mozilla.fenix.ui.pageObjects

class TabsTrayPage {
    class TabsTrayPage {

        val elements =
            {
                "normalBrowsingButton": {
                    // withContentDescription(containsString())
                    "selector": "open tabs. Tap to switch tabs",
                    "strategy": ["Espresso",]
                    "groups": { "requiredForPage": true },
            },
            {
                "privateBrowsingButton": {
                    // onView(withContentDescription())
                    "selector": "Private tabs"
                    "strategy": "Espresso",
                    "groups": { "requiredForPage": true },
                }
            },
            {
                "syncedTabsButton": {
                    // onView(withContentDescription())
                    "selector": "Synced tabs"
                    "strategy": "Espresso",
                    "groups": { "requiredForPage": true },
                }
            },
            {
                "threeDotMenuButton": {
                    // onView(withId())
                    "selector": "R.id.tab_tray_overflow",
                    "strategy": "Espresso"
                    "groups": { "requiredForPage": true },
                }
            },
            {
                "newTabButton": {
                    "selector": "$packageName:id/new_tab_button"
                    "strategy": ["UIAutomator", "itemWithResId"],
                    "groups": { "requiredForPage": true },
                }
            },
    }
}
