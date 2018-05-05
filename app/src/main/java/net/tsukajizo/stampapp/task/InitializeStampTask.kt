package net.tsukajizo.stampapp.task

import android.os.AsyncTask
import kotlinx.coroutines.experimental.async
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Inject

class InitializeStampTask @Inject constructor(private val db: AppDatabase) : AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg parmas: Unit) {
        async {
            if (db.stampDao().count() == 0) {
                val stamp1 = Stamp(0, "Stamp 1", "スタンプ1です", 35.6590746, 139.6984292)
                val stamp2 = Stamp(0, "Stamp 2", "スタンプ2です", 36.6590746, 139.6984292)
                val stamp3 = Stamp(0, "Stamp 3", "スタンプ3です", 37.6590746, 139.6984292)
                val stamp4 = Stamp(0, "Stamp 4", "スタンプ4です", 38.6590746, 139.6984292)
                val stamp5 = Stamp(0, "Stamp 5", "スタンプ5です", 39.6590746, 139.6984292)
                val stamp6 = Stamp(0, "Stamp 6", "スタンプ6です", 40.6590746, 139.6984292)
                val stamp7 = Stamp(0, "Stamp 7", "スタンプ7です", 41.6590746, 139.6984292)
                val stamp8 = Stamp(0, "Stamp 8", "スタンプ8です", 42.6590746, 139.6984292)
                val stamp9 = Stamp(0, "Stamp 9", "スタンプ9です", 43.6590746, 139.6984292)

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
}