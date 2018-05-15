package net.tsukajizo.stampapp.task

import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.data.database.AppDatabase
import javax.inject.Inject

class UpdateGetStampTask @Inject constructor(private val db: AppDatabase) : Task<Int, List<Stamp>>() {
    override fun run(param: Int): List<Stamp> {
        val stamp = db.stampDao().find(param)
        stamp.getStamp()
        db.stampDao().updateStamp(stamp)
        return db.stampDao().findAll()
    }
}