package org.mozilla.fenix.ui.pageObjects

class QuickActionsSheetPage {
    val elements = {
        {
            "quickActionSheet": {
                "selector": "$packageName:id/quick_action_sheet"
                "strategy": ["UIAutomator", "itemWithResId"]
                "groups":{ "requiredForPage": true }
            }
        },
        {
            "quickActionSheetUrl": {
                "selector": ["$packageName:id/url", { "url": url }]
                "strategy": ["UIAutomator", "itemWithResIdContainingText"]
                "groups":{ "requiredForPage": true }
            }
        },

        {
            "quickActionSheetSecurityInfo": {
                "selector": ["$packageName:id/securityInfo", getStringResource(R.string.quick_settings_sheet_secure_connection_2)]
                "strategy": ["UIAutomator", "itemWithResIdContainingText"]
                "groups":{ "requiredForPage": true }
            }
        },
        {
            "quickActionSheetTrackingProtectionSwitch": {
                "selector": "$packageName:id/trackingProtectionSwitch"
                "strategy": ["UIAutomator", "itemWithResId"]
                "groups":
                { "requiredForPage": true },
            }
        },
        {
            "quickActionSheetClearSiteDataButton": {
                "selector": "$packageName:id/clearSiteData",
                "strategy": ["UIAutomator", "itemWithResId"],
                "groups": [
                { "requiredForPage": true },
                ]
            }
        },
    }
}
