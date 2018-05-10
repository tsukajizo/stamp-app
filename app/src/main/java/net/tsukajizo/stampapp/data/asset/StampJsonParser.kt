package net.tsukajizo.stampapp.data.asset

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.data.Stamp

class StampJsonParser(val app: App) {
    fun fromFile(filename: String): List<Stamp> {
        val json = app.resources.assets.open(filename).reader(charset = Charsets.UTF_8).use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, Stamp::class.java)
        val moshi = Moshi.Builder().build()!!
        val adapter: JsonAdapter<List<Stamp>> = moshi.adapter(type)
        return adapter.fromJson(json) ?: throw Exception("asset not found")
    }
}