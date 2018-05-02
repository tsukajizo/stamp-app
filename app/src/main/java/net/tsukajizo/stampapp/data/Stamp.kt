package net.tsukajizo.stampapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.Context
import android.graphics.drawable.Drawable
import net.tsukajizo.stampapp.R

@Entity
data class Stamp constructor(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val label: String,
        val desc: String,
        @ColumnInfo(name = "gather_date")
        var gatherDate: Long = NOT_GATHER_DATE
) {

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