package org.mozilla.fenix.ui.pageObjects

class BrowserPage {
    val elements = {
        {
            // A placeholder for the page content which can be anything: forms, buttons, links?
          "pageContent": {
              "selector": "<anyText>",
              "strategy": ["UIAutomator", "itemWithText"],
              "groups": { "requiredForPage": true },
          }
        },
        {
          "browserLayout": {
              "selector": "$packageName:id/browser_layout",
              "strategy": ["UIAutomator", "itemWithResId"],
              "groups": { "requiredForPage": true },
          }
        },
        {
            "progressBar": {
                "selector": "$packageName:id/mozac_browser_toolbar_progress",
                "strategy": ["UIAutomator", "itemWithResId"],
                "groups": { "requiredForPage": true },
            }
        },
        {
            "suggestedLogins": {
                "selector": "$packageName:id/loginSelectBar",
                "strategy": ["UIAutomator", "itemWithResId"],
                "groups": { "requiredForPage": false },
            }
        },
        {
          "creditCardSuggestion": {
            //How do we pass in the variables?
              "selector": ["$packageName:id/credit_card_number", "$creditCardNumber"],
              "strategy": ["UIAutomator", "itemWithResIdContainingText"],
              "groups": { "requiredForPage": false },
          }
        }
        }
    }
}
