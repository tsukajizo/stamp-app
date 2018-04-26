package net.tsukajizo.stampapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_stamp_vewier.view.*
import net.tsukajizo.stampapp.view.StampListAdapter
import net.tsukajizo.stampapp.view.StampListItemDecoration


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
        rvStampList?.layoutManager = GridLayoutManager(activity, 4)
        rvStampList?.addItemDecoration(StampListItemDecoration(10))
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO "recycle（）の処理の警告に対応する"
        val images = resources.obtainTypedArray(R.array.stamp_blue)
        val adapter = StampListAdapter(activity, images)
        rvStampList?.adapter = adapter
    }

}