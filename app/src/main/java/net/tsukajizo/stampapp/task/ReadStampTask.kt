package net.tsukajizo.stampapp.task

import android.os.AsyncTask
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.database.AppDatabase
import javax.inject.Inject

class ReadStampTask @Inject constructor(private var db: AppDatabase) : AsyncTask<Unit, Unit, List<Stamp>?>() {
    private var listener: TaskListener<List<Stamp>?>? = null
    override fun doInBackground(vararg p0: Unit?): List<Stamp>? {
        return db.stampDao().findAll()
    }

    override fun onPostExecute(result: List<Stamp>?) {
        super.onPostExecute(result)
        listener!!.onSuccess(result)
    }

    fun setListener(listener: TaskListener<List<Stamp>?>) {
        this.listener = listener
    }
}