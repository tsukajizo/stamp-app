package net.tsukajizo.stampapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


public class StampViewerFragment : Fragment() {

    companion object {
        fun newInstance(): StampViewerFragment {
            val fragment = StampViewerFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stamp_vewier, container, false)
    }

}