package com.pronaycoding.blankee.core.common

import org.json.JSONObject

/**
 * Utility object for converting between volume maps and JSON strings.
 *
 * Used for serializing/deserializing preset volume settings for storage in the database.
 * Presets contain volume levels for multiple sounds, which are stored as JSON strings
 * in [PresetEntity] fields (builtInVolumesJson and customVolumesJson).
 *
 * The JSON format only stores sounds with volume > 0 to minimize storage.
 *
 * Example JSON: `{"0":0.5,"2":0.75,"5":1.0}`
 * Maps sound indices/IDs to their volume levels (0.0 to 1.0).
 */
internal object PresetJson {
    /**
     * Converts a map of sound indices/IDs to volumes into a JSON string.
     *
     * Only sound entries with volume > 0 are included in the output to reduce storage size.
     *
     * Example:
     * ```kotlin
     * mapToJson(mapOf(0 to 0.5f, 1 to 0f, 2 to 0.75f))
     * // Returns: {"0":0.5,"2":0.75}
     * ```
     *
     * @param map A map where keys are sound indices/IDs and values are volume levels (0.0 to 1.0)
     * @return JSON string representation of the volume map
     */
    fun mapToJson(map: Map<Int, Float>): String {
        val o = JSONObject()
        map.forEach { (key, value) ->
            if (value > 0f) {
                o.put(key.toString(), value.toDouble())
            }
        }
        return o.toString()
    }

    /**
     * Converts a JSON string back into a map of sound indices/IDs and volumes.
     *
     * Handles blank/empty JSON strings by returning an empty map.
     *
     * Example:
     * ```kotlin
     * jsonToMap("{\"0\":0.5,\"2\":0.75}")
     * // Returns: mapOf(0 to 0.5f, 2 to 0.75f)
     * ```
     *
     * @param json JSON string containing volume mappings
     * @return Map of sound indices/IDs to their volume levels, or empty map if json is blank
     * @throws JSONException if the JSON is malformed
     */
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
