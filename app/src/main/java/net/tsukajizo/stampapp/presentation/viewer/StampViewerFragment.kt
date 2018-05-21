package net.tsukajizo.stampapp.presentation.viewer


import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.dialog_view_stamp_detail.view.*
import kotlinx.android.synthetic.main.fragment_stamp_vewier.view.*
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.task.ReadStampTask
import net.tsukajizo.stampapp.task.UpdateGetStampTask
import net.tsukajizo.stampapp.util.Constant
import javax.inject.Inject


class StampViewerFragment : DaggerFragment() {
    private var rvStampList: RecyclerView? = null

    @Inject
    lateinit var readStampTask: ReadStampTask

    @Inject
    lateinit var updateGetStampTask: UpdateGetStampTask

    @Inject
    lateinit var adapter: StampListAdapter

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
        readStampTask.execute({
            updateStamp(it)
        }, {
            //TODO エラー処理を書く
        }, Unit)

        val stampId = arguments!!.getInt(Constant.BUNDLE_KEY_STAMP_ID, Constant.UNDEFINED_STAMP_ID)
        if (stampId != Constant.UNDEFINED_STAMP_ID) {
            updateGetStampTask.execute({
                val item = it.find { stamp -> stamp.id == stampId }
                if (item != null) {
                    showStampDialog(item, getString(R.string.dialog_title_new_stamp_get))
                }
                updateStamp(it)
            }, {
                //TODO エラー処理を書く
            }, stampId)
        }
    }


    private fun updateStamp(stampList: List<Stamp>) {
        adapter.list = stampList
        adapter.itemClickListener = { stamp -> showStampDialog(stamp) }
        rvStampList?.adapter = adapter
    }

    private fun showStampDialog(item: Stamp, title: String = "") {
        if (activity == null) {
            return
        }
        val dialogView = layoutInflater.inflate(R.layout.dialog_view_stamp_detail, null)
        dialogView.tv_title.text = item.label
        dialogView.tv_desc.text = item.desc
        Glide.with(activity!!).load(item.getStampPath(activity!!)).into(dialogView.iv_stamp_image)
        AlertDialog.Builder(activity).apply {
            setTitle(title)
            setView(dialogView)
            setNegativeButton("OK", null)
        }.create().show()
    }

}