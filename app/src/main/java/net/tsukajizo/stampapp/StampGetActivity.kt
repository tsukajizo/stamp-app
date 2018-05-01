package net.tsukajizo.stampapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

class StampGetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp_get)
        setFragment(QRCameraFragment.newInstance())
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_fragment_container, fragment)
        transaction.commit()
    }
}