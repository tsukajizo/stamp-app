package net.tsukajizo.stampapp.task

import kotlinx.coroutines.experimental.async
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Inject

class InitializeStampTask @Inject constructor(private val db: AppDatabase) : Task<Unit>() {
    override fun execute() {
        async {
            val stamp1 = Stamp(0, "Stamp 1", "スタンプ1です")
            val stamp2 = Stamp(0, "Stamp 2", "スタンプ2です")
            val stamp3 = Stamp(0, "Stamp 3", "スタンプ3です")
            val stamp4 = Stamp(0, "Stamp 4", "スタンプ4です")
            val stamp5 = Stamp(0, "Stamp 5", "スタンプ5です")
            val stamp6 = Stamp(0, "Stamp 6", "スタンプ6です")
            val stamp7 = Stamp(0, "Stamp 7", "スタンプ7です")
            val stamp8 = Stamp(0, "Stamp 8", "スタンプ8です")
            val stamp9 = Stamp(0, "Stamp 9", "スタンプ9です")

            db.stampDao().insertStamp(stamp1)
            db.stampDao().insertStamp(stamp2)
            db.stampDao().insertStamp(stamp3)
            db.stampDao().insertStamp(stamp4)
            db.stampDao().insertStamp(stamp5)
            db.stampDao().insertStamp(stamp6)
            db.stampDao().insertStamp(stamp7)
            db.stampDao().insertStamp(stamp8)
            db.stampDao().insertStamp(stamp9)
        }
    }
}