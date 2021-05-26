package com.examples.data.source.prefrences

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FixedSizeStack<T>(private val sizeLimit: Int) {
    private val entries = mutableListOf<T>()

    fun push(item: T): Boolean {
        if (entries.size < sizeLimit) {
            if (entries.contains(item)) {
                entries.remove(item)
            }
            entries.add(item)
            return true
        } else if (entries.size == sizeLimit) {
            entries.removeFirst()
            if (entries.contains(item)) {
                entries.remove(item)
            }
            entries.add(item)
            return true
        }
        return false
    }

    fun pop(defaultValue: T): T =
        if (entries.isNotEmpty()) entries.removeAt(entries.lastIndex) else defaultValue

    fun peek(defaultValue: T): T =
        if (entries.isNotEmpty()) entries[entries.lastIndex] else defaultValue

    fun getEntries(): List<T> = entries.run {
        val gsonList = Gson().toJson(this)
        return Gson().fromJson<List<T>>(gsonList).asReversed()
    }

    fun clear() {
        entries.clear()
    }

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

}