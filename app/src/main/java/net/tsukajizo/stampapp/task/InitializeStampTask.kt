package net.tsukajizo.stampapp.task

import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.data.asset.StampJsonParser
import net.tsukajizo.stampapp.data.database.AppDatabase
import javax.inject.Inject


class InitializeStampTask @Inject constructor(private val app: App,
                                              private val db: AppDatabase,
                                              private val stampParser: StampJsonParser) : Task<Unit, Unit>() {

    override fun run(param: Unit) {
        if (db.stampDao().count() == 0) {
            val jsonPath = app.resources.getString(R.string.filename_stamp_list_json)
            val stampList = try {
                stampParser.fromAssetFile(jsonPath)
            } catch (e: Throwable) {
                emptyList<Stamp>()
            }
            stampList.forEach { stamp ->
                db.stampDao().insertStamp(stamp)
            }
        }
    }
}