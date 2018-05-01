package net.tsukajizo.stampapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_camera -> {
                    setFragment()
                }
                R.id.nav_stamp -> {
                    setFragment()
                }
                R.id.nav_map -> {
                    setFragment()
                }
                else -> {
                }
            }
            true
        })
        setFragment()
    }

    private fun setFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_fragment_container, StampViewerFragment.newInstance())
        transaction.commit()
    }
}
