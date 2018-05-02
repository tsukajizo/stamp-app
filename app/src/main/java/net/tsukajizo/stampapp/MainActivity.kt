package net.tsukajizo.stampapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.database.AppDatabase
import net.tsukajizo.stampapp.util.Constant
import javax.inject.Inject
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var db: AppDatabase


    companion object {
        const val REQ_GET_STAMP = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.app()!!.appComponent()!!.inject(this)

        initializeStampData()

        bottom_navigation.setOnNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_camera -> {
                    setFragment(StampViewerFragment.newInstance())
                }
                R.id.nav_stamp -> {
                    setFragment(StampViewerFragment.newInstance())
                }
                R.id.nav_map -> {
                    setFragment(StampViewerFragment.newInstance())
                }
                else -> {
                }
            }
            true
        })
        fab_get_stamp.setOnClickListener({
            startGetStampActivity()
        })
        setFragment(StampViewerFragment.newInstance())
    }

    private fun initializeStampData() {
        // DummyCode
        val stamp1 = Stamp(0, "Stamp 1", "スタンプ1です")
        val stamp2 = Stamp(0, "Stamp 2", "スタンプ2です")
        val stamp3 = Stamp(0, "Stamp 3", "スタンプ3です")
        val stamp4 = Stamp(0, "Stamp 4", "スタンプ4です")
        val stamp5 = Stamp(0, "Stamp 5", "スタンプ5です")
        val stamp6 = Stamp(0, "Stamp 6", "スタンプ6です")
        val stamp7 = Stamp(0, "Stamp 7", "スタンプ7です")
        val stamp8 = Stamp(0, "Stamp 8", "スタンプ8です")
        val stamp9 = Stamp(0, "Stamp 9", "スタンプ9です")
        thread {
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
        thread {
            val stampList = db.stampDao().findAll()
            Log.d("Main", "stamp=${stampList}")
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_GET_STAMP -> {
                val fragment = StampViewerFragment.newInstance()
                if (data != null) {
                    val stampId = data.getIntExtra(Constant.BUNDLE_KEY_STAMP_ID, Constant.UNDEFINED_STAMP_ID)
                    val bundle = Bundle()
                    bundle.putInt(Constant.BUNDLE_KEY_STAMP_ID, stampId)
                    fragment.arguments = bundle
                }
                setFragment(fragment)
            }
        }
    }

    private fun startGetStampActivity() {
        val intent = Intent(this, StampGetActivity::class.java)
        startActivityForResult(intent, REQ_GET_STAMP)
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_fragment_container, fragment)
        transaction.commit()
    }
}
