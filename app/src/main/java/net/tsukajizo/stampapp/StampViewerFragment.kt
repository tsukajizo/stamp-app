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
import net.tsukajizo.stampapp.util.Constant
import net.tsukajizo.stampapp.view.StampListAdapter
import net.tsukajizo.stampapp.view.StampListItemDecoration


class StampViewerFragment : Fragment() {
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

        // DummyCode
        val stamp1 = Stamp(0, "Stamp 1", "スタンプ1です")
        val stamp2 = Stamp(1, "Stamp 2", "スタンプ2です")
        val stamp3 = Stamp(2, "Stamp 3", "スタンプ3です")
        val stamp4 = Stamp(3, "Stamp 4", "スタンプ4です")
        val stamp5 = Stamp(4, "Stamp 5", "スタンプ5です")
        val stamp6 = Stamp(5, "Stamp 6", "スタンプ6です")
        val stamp7 = Stamp(6, "Stamp 7", "スタンプ7です")
        val stamp8 = Stamp(7, "Stamp 8", "スタンプ8です")
        val stamp9 = Stamp(8, "Stamp 9", "スタンプ9です")
        stamp6.getStamp()
        stamp8.getStamp()
        val stampList: List<Stamp> = listOf(
                stamp1, stamp2, stamp3, stamp4, stamp5, stamp6, stamp7, stamp8, stamp9
        )

        val adapter = StampListAdapter(activity, stampList, object : StampListAdapter.OnItemClickListener {
            override fun onClick(item: Stamp) {
                Toast.makeText(activity, "label:${item.label} , desc:${item.desc}", Toast.LENGTH_LONG).show()
            }
        })
        rvStampList?.adapter = adapter

        val stampId = arguments.getInt(Constant.BUNDLE_KEY_STAMP_ID, Constant.UNDEFINED_STAMP_ID)
        if (stampId != Constant.UNDEFINED_STAMP_ID) {
            Toast.makeText(activity, "新しいスタンプをGET!", Toast.LENGTH_SHORT).show()
        }
    }

}