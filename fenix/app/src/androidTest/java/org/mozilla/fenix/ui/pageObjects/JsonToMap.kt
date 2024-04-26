package org.mozilla.fenix.ui.pageObjects

import mozilla.components.support.ktx.android.org.json.toList
import org.json.JSONArray
import org.json.JSONObject

object JsonToMap {
    fun JSONObject.toMap(): Map<String, Any> {
        val keys = keys()
        val map = mutableMapOf<String, Any>()
        while (keys.hasNext()) {
            val key = keys.next()
            val value = this.get(key)
            if (value is JSONObject) {
                map[key] = value.toMap()
            } else if (value is JSONArray) {
                map[key] = value.toList()
            } else {
                map[key] = value
            }
        }
        return map
    }
}
