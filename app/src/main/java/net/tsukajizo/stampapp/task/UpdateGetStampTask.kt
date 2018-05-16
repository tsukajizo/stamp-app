package net.tsukajizo.stampapp.task

import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.data.database.AppDatabase
import javax.inject.Inject

class UpdateGetStampTask @Inject constructor(private val db: AppDatabase) : Task<Int, List<Stamp>, Unit>() {
    override fun run(param: Int): Result<List<Stamp>, Unit> {
        val stamp = db.stampDao().find(param)
        stamp.getStamp()
        db.stampDao().updateStamp(stamp)
        return Result.Success(db.stampDao().findAll())
    }
}