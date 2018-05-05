package net.tsukajizo.stampapp.presentation.title

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tsukajizo.stampapp.R

class TitleStampRallyFragment : Fragment() {
    companion object {
        fun newInstance(): TitleStampRallyFragment {
            val fragment = TitleStampRallyFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_title_stamp_rally, container, false)
        return view
    }
}