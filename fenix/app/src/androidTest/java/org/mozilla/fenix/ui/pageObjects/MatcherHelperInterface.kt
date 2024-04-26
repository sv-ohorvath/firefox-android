package org.mozilla.fenix.ui.pageObjects

import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import org.mozilla.fenix.helpers.TestHelper.mDevice

class MatcherHelperInterface {
    // strategy interface
    // look ma, currying
    // modern implementation is with partial functions or decorators
    fun itemWith(selectorStrategy: string, selector: string): UiObject {
        // strategy: {
        //     ResId: ,
        //     ContainingText: ,
        //     Text: ,
        //     Description: ,
        // }

        // fun getElementObject(selector: string) {
        //     return mDevice.findObject(UiSelector().strategy(selector))


        // Log.i(TAG, "Looking for item with resource id: $resourceId and containing text: $text")
        // return getElementObject

        // The Adapater Pattern
        // usage: itemWith(element.strategy[1])
        return mDevice.findObject(UiSelector().selectorStrategy(selector))
    }
}
