package net.tsukajizo.stampapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.data.StampDao

@Database(entities = [(Stamp::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stampDao(): StampDao
}