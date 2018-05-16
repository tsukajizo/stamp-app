package net.tsukajizo.stampapp.task

import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.data.database.AppDatabase
import javax.inject.Inject

class ReadStampTask @Inject constructor(private var db: AppDatabase) : Task<Unit, List<Stamp>, Unit>() {

    override fun run(param: Unit): Result<List<Stamp>, Unit> {
        return Result.Success(db.stampDao().findAll())
    }
}