package net.tsukajizo.stampapp.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.presentation.collect.StampGetActivity
import net.tsukajizo.stampapp.presentation.location.StampLocationFragment
import net.tsukajizo.stampapp.presentation.title.TitleStampRallyFragment
import net.tsukajizo.stampapp.presentation.viewer.StampViewerFragment
import net.tsukajizo.stampapp.util.Constant
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    companion object {
        const val REQ_GET_STAMP = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.nav_camera -> {
                    setFragment(TitleStampRallyFragment.newInstance())
                }
                R.id.nav_stamp -> {
                    setFragment(StampViewerFragment.newInstance())
                }
                R.id.nav_map -> {
                    setFragment(StampLocationFragment.newInstance())
                }
                else -> {
                }
            }
            true
        })
        fab_get_stamp.setOnClickListener({
            startGetStampActivity()
        })
        setFragment(TitleStampRallyFragment.newInstance())
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
