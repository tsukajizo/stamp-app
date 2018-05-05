package net.tsukajizo.stampapp.presentation.location

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tsukajizo.stampapp.R

class StampLocationFragment : Fragment() {


    companion object {
        fun newInstance(): StampLocationFragment {
            val fragment = StampLocationFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stamp_location, container, false)
        return view
    }
}