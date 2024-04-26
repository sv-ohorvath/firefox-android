package org.mozilla.fenix.ui.pageObjects

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import java.io.File
import java.io.IOException
import org.json.JSONObject
import org.junit.Assert.assertTrue
import org.mozilla.fenix.GleanMetrics.MetaAttribution.app
import org.mozilla.fenix.helpers.Constants.LONG_CLICK_DURATION
import org.mozilla.fenix.helpers.Constants.TAG
import org.mozilla.fenix.helpers.HomeActivityComposeTestRule
import org.mozilla.fenix.helpers.MatcherHelper.assertUIObjectExists
import org.mozilla.fenix.helpers.MockBrowserDataHelper
import org.mozilla.fenix.helpers.MockBrowserDataHelper.context
import org.mozilla.fenix.helpers.TestAssetHelper.waitingTime
import org.mozilla.fenix.helpers.TestHelper.mDevice
import org.mozilla.fenix.helpers.isChecked
import org.mozilla.fenix.ui.pageObjects.JsonToMap.toMap
import org.mozilla.fenix.ui.pageObjects.BasePage.selectors.json as selectors
import org.mozilla.fenix.helper.MatcherHelper as UIAutomatorMatchers

// Interface Mapping
config: {

}

strategyMatcher: {

}

open class BasePage {
    fun readSelectorsFromFile(filePath: String): JSONObject {
        val fileContent = File(filePath).readText()
        return JSONObject(fileContent)
    }

    val selectorsFile = readSelectorsFromFile("BasePage.selectors.json")

    var selectors: Map<String, Any> = selectorsFile.toMap()

    fun getElement(element: JSONObject, composeTestRule: ComposeTestRule? = null) {
        when (element.strategy) {
            // strategy: [ "UIAutomator", "ResId" ]
            "UIAutomator" -> UIAutomatorMatchers.itemWith(element.strategy[1], element.selector)
            "UIAutomator2" -> UIAutomator2Matchers.itemBy()
        "Espresso" -> onView(withText(element.selectorData))
            "Compose" -> composeTestRule?.onNodeWithText(element.selectorData)
        }

        /* Original
            // UiAutomator: mDevice.findObject(UiSelector().textContains(text))
            // Espresso: onView(withText("Bookmarks"))
            // Compose: composeTestRule.onNodeWithText(g"text))
            fun useCompose(element, logging) {
                // TODO: refactor to make dynamic test rule importing
                private val composeTestRule: HomeActivityComposeTestRule
                return composeTestRule.onNodeWithText(element.selectorData)
            }

            when (element.strategy[0]) {
                // strategy: [ "UIAutomator", "ResId" ]
                "UIAutomator" -> UIAutomatorMatchers.itemWith(element.strategy[1], element.selector),
            "UIAutomator2" -> UIAutomator2Matchers.itemBy(),
            "Espresso" -> onView(withText(element.selectorData)),
            "Compose" -> useCompose(element)
            }
        */
    }

    fun getElementByGroup(group: String): List<JSONObject> {
        return selectors.filter { it.groups.contains(group) }
    }

    fun waitForElementToExist(element: ??) {
        //val el = this.getElement(element) // returns element object --> this should be done before calling this function

        // TODO: Add color to these. yellow for 'waiting...', green for 'found', 'red' for 'not found'
        Log.i(TAG,"waiting for {element} to exist...")
        when (element.strategy) {
            "UIAutomator" -> assertTrue(element.waitForExists())
            "Espresso" -> element.check(matches(isDisplayed()))
            "Compose" -> element.assertIsDisplayed()
        }
        Log.i(TAG,"{element} found! Moving on...")
    }

    // custom commands
    fun waitForPageToLoad() {
        // returns list of json objects of each object
        val requiredElements = this.getElementByGroup("requiredForPage")

        // TODO: verify this is async behavior
        // check concurrently for each element
        for (element in requiredElements)
            waitForElementToExist(element)
    }

    // _actually_ the custom commands
    // Swipe, SetText, Click, Scroll, ClearText, LongClick
    fun mozClick(element: JSONObject) {
        val el = getElement(element)

        // TODO: make this purple in the logs
        Log.i(TAG,"trying to click ${element}...")
        waitForElementToExist(el)
        when (el.strategy) {
            "UIAutomator" -> el.click()
            "Espresso" -> el.perform(click())
            "Compose" -> el.performClick()
        }
        Log.i(TAG,"clicked $element successfully! Moving on...")
        return this
    }

    fun mozSwipe(element: JSONObject, swipeDirection: String, steps: Int) {
        val el = getElement(element)
        waitForElementToExist(el)
        when {
            el.strategy == "UIAutomator"  && swipeDirection == "down" -> el.swipeDown(steps)
            el.strategy == "Espresso"  && swipeDirection == "down" -> el.perform(swipeDown())
            el.strategy == "Compose"  && swipeDirection == "down" -> el.performTouchInput { swipeDown() }
        }
        when {
            el.strategy == "UIAutomator"  && swipeDirection == "up" -> el.swipeUp(steps)
            el.strategy == "Espresso"  && swipeDirection == "up" -> el.perform(swipeUp())
            el.strategy == "Compose"  && swipeDirection == "up" -> el.performTouchInput { swipeUp() }
        }
        when {
            el.strategy == "UIAutomator"  && swipeDirection == "left" -> el.swipeLeft(steps)
            el.strategy == "Espresso"  && swipeDirection == "left" -> el.perform(swipeLeft())
            el.strategy == "Compose"  && swipeDirection == "left" -> el.performTouchInput { swipeLeft() }
        }
        when {
            el.strategy == "UIAutomator"  && swipeDirection == "right" -> el.swipeRight(steps)
            el.strategy == "Espresso"  && swipeDirection == "right" -> el.perform(swipeRight())
            el.strategy == "Compose"  && swipeDirection == "right" -> el.performTouchInput { swipeRight() }
        }
        return this
    }

    fun mozSetText(element: JSONObject, text: String) {
        val el = getElement(element)
        waitForElementToExist(el)

        when (element.strategy) {
            "UIAutomator" -> el.setText(text)
            "Espresso" -> el.perform(ViewActions.typeText(text))
            // We don't have any text setting using compose in our UI tests
            // Decided to use this one as it clear the text before setting the new one (similar to UIAutomator)
            "Compose" -> el.performTextReplacement(text)
        }
        return this
    }

    fun mozClearText(element: JSONObject) {
        val el = getElement(element)
        waitForElementToExist(el)

        when(el.strategy) {
            "UIAutomator" -> el.clearTextField()
            "Espresso" -> el.perform(ViewActions.clearText())
            "Compose" -> el.performTextClearance()
        }
        return this
    }

    fun mozLongClick(element: JSONObject) {
        val el = getElement(element)
        waitForElementToExist(el)

        when(el.strategy) {
            "UIAutomator" -> el.longClick()
            "UIAutomator2" -> el.click(LONG_CLICK_DURATION)
            "Espresso" -> el.perform(ViewActions.longClick())
            "Compose" -> el.performTouchInput { longClick() }
        }
        return this
    }

    fun mozScrollToElement(element: JSONObject, scrollDirection: String) {
        val el = getElement(element)
        waitForElementToExist(el)
        when {
            el.strategy == "Espresso" && scrollDirection == "toElement"-> el.perform(scrollTo()) //scrolls to the element
             //UIAutomator: UiScrollable(UiSelector().resourceId("pocket.stories")).scrollForward(steps)
            // UiScrollable(UiSelector().resourceId("pocket.stories")).scrollIntoView(UiSelector().text("text"))
            el.strategy == "Compose"  && scrollDirection == "toElement" -> el.performScrollToIndex(el.index)// scrolls a list to a child element
        }
        return this
    }

    fun mozScrollList(listElement: JSONObject, childElement: JSONObject, scrollDirection: String, steps: Int) {
        val listEl = getElement(listElement)
        val childEl = getElement(childElement)
        waitForElementToExist(el)
        when {
            listEl.strategy == "UIAutomator" && scrollDirection == "up" -> listEl.scrollBackward(steps)
            listEl.strategy == "UIAutomator" && scrollDirection == "down" -> listEl.scrollForward(steps)
            listEl.strategy == "UIAutomator"  && scrollDirection == "toElement" -> listElement.scrollIntoView(childEl) //scrolls a list to find element
        }
        return this
    }

    // Use DFS to build page navigation path
    fun navigateToPage(navigationType: String, jsonObject: JSONObject) {
        when (navigationType) {
            "default" -> self.NavigationPath().fromDefault
            "custom" -> self.NavigationPath().fromCustom
            "deepLink" -> self.NavigationPath().fromDeeplink
        }
        // DFS algorithm here
        // two issues we need to address:
        // 1. factorial complexity (memory overflow during exec)
        // proposed solution: use backtracking
        // 2. infinite recursion/can't find entrypoint
        // proposed solution: flags to mark pages that don't lead to TestRule
        // from pageObject, self
        //1.starting with 1st key in 'From.default'
        //2. look in pageObject for 'TestRule'
        //2a. if 'TestRule' is found the add to 'navigation array'
        //2b. loop back to 1
        //3. add each key/value in 'From.default' to navigation array
        //4. execute each nav action from the call stack

        // traverse json objects navigation paths in preordered Depth-first search
        if (jsonObject.has("default")) {
            val defaultPath = jsonObject.getJSONObject("default")
            for (key in defaultPath.keys()) {
                val value = defaultPath.get(key)
                if (value is JSONObject) {
                    navigateToPage("default", value)
                }
            }
        } else if (jsonObject.has("custom")) {
            // if no default path, then look for custom path or deep link
                val customPath = jsonObject.getJSONObject("custom")
                for (key in customPath.keys()) {
                    val value = customPath.get(key)
                    if (value is JSONObject) {
                        navigateToPage("custom", value)
                    }
                }
            } else {
                if (jsonObject.has("deepLink")) {
                    val deepLink = jsonObject.getString("deepLink")
                    // navigate to deep link
                // navigateToPage("deepLink", value)

                }
            }
        }

    }
}
