package net.tsukajizo.stampapp.task

import android.os.AsyncTask
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Inject

class UpdateGetStampTask @Inject constructor(private val db: AppDatabase) : AsyncTask<Int, Unit, List<Stamp>?>() {
    private var successListener: TaskSuccessListener<List<Stamp>?>? = null
    override fun doInBackground(vararg stampIds: Int?): List<Stamp>? {
        val stampId = stampIds[0]!!
        val stamp = db.stampDao().find(stampId)
        stamp.getStamp()
        db.stampDao().updateStamp(stamp)
        return db.stampDao().findAll()
    }

    override fun onPostExecute(result: List<Stamp>?) {
        super.onPostExecute(result)
        successListener!!.onSuccess(result)
    }

    fun setListener(listener: TaskSuccessListener<List<Stamp>?>) {
        this.successListener = listener
    }

}