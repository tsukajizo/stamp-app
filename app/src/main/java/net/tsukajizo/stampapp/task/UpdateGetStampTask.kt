package net.tsukajizo.stampapp.task

import kotlinx.coroutines.experimental.async
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Inject

class UpdateGetStampTask @Inject constructor(private val db: AppDatabase) : Task<List<Stamp>?>() {

    var stampId: Int = 0

    override fun execute(): List<Stamp>? {
        var stampList: List<Stamp>? = null
        async {
            val stamp = db.stampDao().find(stampId)
            stamp.getStamp()
            db.stampDao().updateStamp(stamp)
            stampList = db.stampDao().findAll()
        }
        return stampList
    }

}