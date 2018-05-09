package net.tsukajizo.stampapp.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import net.tsukajizo.stampapp.data.Stamp


@Dao
interface StampDao {

    @Insert
    fun insertStamp(stamp: Stamp)

    @Update
    fun updateStamp(stamp: Stamp)

    @Query("SELECT * FROM Stamp")
    fun findAll(): List<Stamp>

    @Query("SELECT * FROM Stamp WHERE id = :id LIMIT 1")
    fun find(id: Int): Stamp


    @Query("SELECT COUNT(*) FROM Stamp")
    fun count(): Int
}