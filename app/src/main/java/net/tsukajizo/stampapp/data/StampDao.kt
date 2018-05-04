package net.tsukajizo.stampapp.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update


@Dao
interface StampDao {

    @Insert
    fun insertStamp(stamp: Stamp)

    @Update
    fun updateStamp(stamp: Stamp)

    @Query("SELECT * FROM Stamp")
    fun findAll(): List<Stamp>

    @Query("SELECT COUNT(*) FROM Stamp")
    fun count(): Int
}