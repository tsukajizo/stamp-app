package net.tsukajizo.stampapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_camera -> {
                    setFragment(QRCameraFragment.newInstance())
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
        setFragment(StampViewerFragment.newInstance())
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_fragment_container, fragment)
        transaction.commit()
    }
}
