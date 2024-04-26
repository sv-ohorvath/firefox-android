package org.mozilla.fenix.ui.pageObjects

class SearchPage {
    class SearchPage {

        val elements = {
            {
                "scanButton": {
                    "selector": "Scan",
                    "strategy": ["UIAutomator", "itemWithDescription"]
                    "groups": { "requiredForPage": true },
                }
            },
            {
                "voiceSearchButton": {
                    "selector": "Voice search"
                    "strategy": ["UIAutomator", "itemWithDescription"]
                    "groups": { "requiredForPage": true },
              }
            },
            {
                "searchWrapper": {
                    "selector": "$packageName:id/search_wrapper"
                    "strategy": ["UIAutomator", "itemWithResId"],
                    "groups":{ "requiredForPage": true },
                }
            },
            {
                "toolbarInEditMode": {
                    "selector": "$packageName:id/mozac_browser_toolbar_edit_url_view",
                    "strategy": ["UIAutomator", "itemWithResId"],
                    "groups": { "requiredForPage": true },
                }
            }
    }
}
