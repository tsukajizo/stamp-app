package net.tsukajizo.stampapp.task

import android.os.AsyncTask
import kotlinx.coroutines.experimental.async
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.data.asset.StampJsonParser
import net.tsukajizo.stampapp.data.database.AppDatabase
import javax.inject.Inject


class InitializeStampTask @Inject constructor(private val app: App,
                                              private val db: AppDatabase,
                                              private val stampParser: StampJsonParser) : AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg parmas: Unit) {
        async {
            if (db.stampDao().count() == 0) {
                val jsonPath = app.resources.getString(R.string.filename_stamp_list_json)
                val stampList = try {
                    stampParser.fromAssetFile(jsonPath)
                } catch (e: Throwable) {
                    null
                }
                stampList?.forEach { stamp ->
                    db.stampDao().insertStamp(stamp)
                }
            }
        }
    }
}