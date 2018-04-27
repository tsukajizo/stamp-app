package net.tsukajizo.stampapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_stamp_vewier.view.*
import net.tsukajizo.stampapp.data.Stamp
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
        val stampList: List<Stamp> = listOf(
                Stamp(0, "Stamp 1", "スタンプ1です"),
                Stamp(1, "Stamp 2", "スタンプ2です"),
                Stamp(2, "Stamp 3", "スタンプ3です"),
                Stamp(3, "Stamp 4", "スタンプ4です"),
                Stamp(4, "Stamp 5", "スタンプ5です"),
                Stamp(5, "Stamp 6", "スタンプ6です"),
                Stamp(6, "Stamp 7", "スタンプ7です"),
                Stamp(7, "Stamp 8", "スタンプ8です"),
                Stamp(8, "Stamp 9", "スタンプ9です")
        )

        val adapter = StampListAdapter(activity, stampList, object : StampListAdapter.OnItemClickListener {
            override fun onClick(item: Stamp) {
                Toast.makeText(activity, "label:${item.label} , desc:${item.desc}", Toast.LENGTH_LONG).show()
            }
        })
        rvStampList?.adapter = adapter
    }

}