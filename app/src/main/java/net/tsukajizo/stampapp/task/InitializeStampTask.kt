package net.tsukajizo.stampapp.task

import android.os.AsyncTask
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.experimental.async
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.data.database.AppDatabase
import javax.inject.Inject


class InitializeStampTask @Inject constructor(private val db: AppDatabase) : AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg parmas: Unit) {
        async {
            if (db.stampDao().count() == 0) {
                // JSON データを assets から取得
                val json = App.app()?.resources?.assets?.open("stamp_list.json")?.reader(charset = Charsets.UTF_8).use { it?.readText() }
                        ?: throw Exception("asset not found")
                val type = Types.newParameterizedType(List::class.java, Stamp::class.java)
                val moshi = Moshi.Builder().build()!!
                val adapter: JsonAdapter<List<Stamp>> = moshi.adapter(type)
                val stampList = adapter.fromJson(json)
                stampList?.forEach { stamp ->
                    db.stampDao().insertStamp(stamp)
                }
            }
        }
    }
}