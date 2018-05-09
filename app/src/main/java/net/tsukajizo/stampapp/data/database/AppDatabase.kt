package net.tsukajizo.stampapp.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.tsukajizo.stampapp.data.Stamp

@Database(entities = [(Stamp::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stampDao(): StampDao
}