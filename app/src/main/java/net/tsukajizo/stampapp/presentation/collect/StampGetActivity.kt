package net.tsukajizo.stampapp.presentation.collect

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.presentation.Navigator
import javax.inject.Inject

class StampGetActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp_get)
        navigator.navigateToQRCameraFragment()
    }
}