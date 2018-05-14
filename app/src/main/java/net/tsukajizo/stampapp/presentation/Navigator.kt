package net.tsukajizo.stampapp.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.presentation.collect.StampGetActivity
import net.tsukajizo.stampapp.presentation.location.StampLocationFragment
import net.tsukajizo.stampapp.presentation.title.TitleStampRallyFragment
import net.tsukajizo.stampapp.presentation.viewer.StampViewerFragment
import net.tsukajizo.stampapp.util.Constant
import javax.inject.Inject

class Navigator @Inject constructor(private val activity: AppCompatActivity) {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    fun navigateToStampList() {
        navigateToStampList(Constant.UNDEFINED_STAMP_ID)
    }

    fun navigateToStampList(stampId: Int) {
        val fragment = StampViewerFragment.newInstance()
        val bundle = Bundle()
        bundle.putInt(Constant.BUNDLE_KEY_STAMP_ID, stampId)
        fragment.arguments = bundle
        replaceFragment(StampViewerFragment.newInstance())
    }

    fun navigateToStampLocation() {
        replaceFragment(StampLocationFragment.newInstance())
    }

    fun navigateToTitleStampRally() {
        replaceFragment(TitleStampRallyFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fl_fragment_container, fragment)
        transaction.commit()
    }

    fun navigateToGetStampActivity(requestId: Int) {
        val intent = Intent(activity, StampGetActivity::class.java)
        activity.startActivityForResult(intent, requestId)
    }
}
