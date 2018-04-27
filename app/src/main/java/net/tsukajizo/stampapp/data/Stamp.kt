package net.tsukajizo.stampapp.data

import android.content.Context
import android.graphics.drawable.Drawable
import net.tsukajizo.stampapp.R

data class Stamp(val id: Int, val label: String, val desc: String, var gatherDate: Long = NOT_GATHER_DATE) {
    companion object {
        const val NOT_GATHER_DATE: Long = 0L
    }

    val isGathered: Boolean
        get() = gatherDate != NOT_GATHER_DATE

    fun getStamp() {
        if (!isGathered) {
            gatherDate = System.currentTimeMillis()
        }
    }

    fun getStampDrawable(context: Context): Drawable {
        val images = context.resources.obtainTypedArray(R.array.stamp_blue)
        val drawable = images.getDrawable(id)
        images.recycle()
        return drawable
    }

}