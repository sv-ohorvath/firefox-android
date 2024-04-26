package org.mozilla.fenix.ui.pageObjects

import org.mozilla.fenix.helpers.TestHelper.packageName

class NavigationBarComponent {
   val elements = {
        {
            "urlBar": {
                "selector": "$packageName:id/toolbar"
                "strategy": ["UIAutomator", "itemWithResId"],
                "groups": { "requiredForPage": true },
            }
        },
        {
            "searchSelectorButton": {
                "selector": "$packageName:id/search_selector"
                "strategy": ["UIAutomator", "itemWithResId"],
                "groups": { "requiredForPage": true },
            }
        },
        {
            "mainMenuButton": {
                "selector": "R.id.menuButton"
                "strategy": "Espresso",
                "groups": { "requiredForPage": true }
            }
        },
        {
            //This is the onWebPage variation of the mainMenuButton, with a different locator.
            // Should it belong to BrowserPage?
            "browserPageMenuButton": {
                "selector": "R.id.mozac_browser_toolbar_menu"
                "strategy": "Espresso",
                "groups": [
                    { "requiredForPage": true },
                    {"onWebPage": true}
                ]
            }
        },
        {
            "tabTrayButton": {
                "selector": "R.id.tab_button"
                "strategy": "Espresso",
                "groups": { "requiredForPage": true },
            }
        },
        {
            "tabCounter": {
                "selector": ["$packageName:id/counter_text", {"numberOfOpenTabs": numberOfOpenTabs}]
                "strategy": ["UIAutomator", "itemWithResIdAndText"],
                "groups": { "requiredForPage": true }
            }
        },
    }
}
