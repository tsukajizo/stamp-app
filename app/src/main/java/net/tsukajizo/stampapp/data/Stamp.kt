package net.tsukajizo.stampapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.Context
import net.tsukajizo.stampapp.R

@Entity
data class Stamp constructor(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val label: String,
        val desc: String,
        val latitude: Double,
        val longitude: Double,

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

    fun getStampPath(context: Context): String {
        return String.format(context.getString(R.string.stamp_path_format), id)
    }

}