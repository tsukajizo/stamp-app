package net.tsukajizo.stampapp.task

import kotlinx.coroutines.experimental.async
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Inject

class ReadStampTask @Inject constructor(private var db: AppDatabase) : Task<List<Stamp>?>() {
    override fun execute(): List<Stamp>? {
        var stampList: List<Stamp>? = null
        async {
            stampList = db.stampDao().findAll()

        }
        return stampList
    }
}