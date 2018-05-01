package net.tsukajizo.stampapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val REQ_GET_STAMP = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
