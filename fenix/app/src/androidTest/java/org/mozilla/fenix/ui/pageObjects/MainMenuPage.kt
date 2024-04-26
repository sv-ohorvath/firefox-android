package org.mozilla.fenix.ui.pageObjects

import org.json.JSONObject
import org.mozilla.fenix.ui.pageObjects.JsonToMap.toMap

class MainMenuComponent(state: String): BasePage() {
    private var stateSelectors: Map<String, Any>
    private var requiredSelectors: Map<String, Any>
    init {
        stateSelectors = this.selectors.toMap().filter { it.value == state }
        requiredSelectors = this.selectors.toMap().filter { it.value == "requiredByPage" }
    }

    navigationPath {
        "From":
            "default": {"HomeScreenPage": "mozClick(mainMenuButton)" }
    }

    // TODO: Explain what the groups are for in a comment here.
    // Groups:
    // requiredForPage: true - add when element is both within the Viewport upon reaching the page,
    //      and required for the page to function, regardless of state.
    // onWebpage: true - add when element is only required when a webpage is open, otherwise not present on the Homepage.
    val elements = [
        {
            "bookmarksButton": {
                "selector": "Bookmarks",
                "strategy": ["UIAutomator", "itemWithText"],
                "groups":
                    { "requiredForPage": true },
            }
        },

        //!! Required for Page - only when a webpage is open - how do we separate these?
        // same for Report site issue button, and Add to home screen button, etc
        // maybe HomeScreenMenuPage and WebPageMenuPage?
        {
            "addBookmarkButton": {
                "selector":["$packageName:id/checkbox", "Add"],
                "strategy":["UIAutomator", "itemWithResIdAndText"],
                "groups":
                    { "requiredForPage": true },
                     { "onWebpage": true}
            }
        },
        {
            "historyButton": {
                "selector": "History"
                "strategy": ["UIAutomator", "itemWithText"],
                "groups":
                    { "requiredForPage": true }
            }
        },
        {
            "downloadsButton": {
                "selector": "Downloads"
                "strategy": ["UIAutomator", "itemWithText"],
                "groups":
                    { "requiredForPage": true },
            }
        },
        {
            "desktopSiteButton": {
                "selector": "Desktop site",
                "strategy": ["UIAutomator", "itemWithText"],
                "groups":
                    { "requiredForPage": true },
            }
        }
    ]

//    val state = object {
//        val default= self.getElementByGroup("requiredForPage")
//        val onWebpage= self.getElementByGroup("onWebpage")
//    }
}
