package net.tsukajizo.stampapp.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.util.Constant
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    companion object {
        const val REQ_GET_STAMP = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_camera -> {
                    navigator.navigateToTitleStampRally()
                }
                R.id.nav_stamp -> {
                    navigator.navigateToStampList()
                }
                R.id.nav_map -> {
                    navigator.navigateToStampLocation()
                }
                else -> {
                }
            }
            true
        })
        fab_get_stamp.setOnClickListener({
            navigator.navigateToGetStampActivity(REQ_GET_STAMP)
        })
        navigator.navigateToTitleStampRally()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_GET_STAMP -> {
                if (resultCode == Activity.RESULT_OK) {
                    val stampId = data?.getIntExtra(Constant.BUNDLE_KEY_STAMP_ID, Constant.UNDEFINED_STAMP_ID)
                            ?: Constant.UNDEFINED_STAMP_ID
                    navigator.navigateToStampList(stampId)
                }
            }
        }
    }

}
