package com.pronaycoding.blanket_mobile.data.local

import org.json.JSONObject

internal object PresetJson {
    fun mapToJson(map: Map<Int, Float>): String {
        val o = JSONObject()
        map.forEach { (key, value) ->
            if (value > 0f) {
                o.put(key.toString(), value.toDouble())
            }
        }
        return o.toString()
    }

    fun jsonToMap(json: String): Map<Int, Float> {
        if (json.isBlank()) return emptyMap()
        val o = JSONObject(json)
        val out = mutableMapOf<Int, Float>()
        val keys = o.keys()
        while (keys.hasNext()) {
            val k = keys.next()
            out[k.toInt()] = o.getDouble(k).toFloat()
        }
        return out
    }
}
