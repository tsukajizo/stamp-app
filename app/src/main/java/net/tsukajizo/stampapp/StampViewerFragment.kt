package net.tsukajizo.stampapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_stamp_vewier.view.*


public class StampViewerFragment : Fragment() {
    var rvStampList: RecyclerView? = null

    companion object {
        fun newInstance(): StampViewerFragment {
            val fragment = StampViewerFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stamp_vewier, container, false)
        rvStampList = view.rv_stamp_list
        rvStampList?.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val array = ArrayList<String>()
        array.add("A")
        array.add("B")
        array.add("C")
        val adapter = StampListAdapter(activity, array)
        rvStampList?.adapter = adapter
    }

}