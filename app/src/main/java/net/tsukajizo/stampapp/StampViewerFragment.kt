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
import net.tsukajizo.stampapp.task.ReadStampTask
import net.tsukajizo.stampapp.task.TaskSuccessListener
import net.tsukajizo.stampapp.task.UpdateGetStampTask
import net.tsukajizo.stampapp.util.Constant
import net.tsukajizo.stampapp.view.StampListAdapter
import net.tsukajizo.stampapp.view.StampListItemDecoration
import javax.inject.Inject


class StampViewerFragment : Fragment() {
    private var rvStampList: RecyclerView? = null

    @Inject
    lateinit var readStampTask: ReadStampTask

    @Inject
    lateinit var updateGetStampTask: UpdateGetStampTask

    companion object {
        fun newInstance(): StampViewerFragment {
            val fragment = StampViewerFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.app()!!.appComponent()!!.inject(this)
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
        readStampTask.setListener(object : TaskSuccessListener<List<Stamp>?> {
            override fun onSuccess(t: List<Stamp>?) {
                super.onSuccess(t)
                if (t != null) {
                    updateStamp(t)
                }
            }
        })
        readStampTask.execute()

        val stampId = arguments.getInt(Constant.BUNDLE_KEY_STAMP_ID, Constant.UNDEFINED_STAMP_ID)
        if (stampId != Constant.UNDEFINED_STAMP_ID) {
            updateGetStampTask.setListener(object : TaskSuccessListener<List<Stamp>?> {
                override fun onSuccess(t: List<Stamp>?) {
                    super.onSuccess(t)
                    if (t != null) {
                        updateStamp(t)
                    }
                }
            })
            updateGetStampTask.execute(stampId)
            Toast.makeText(activity, "新しいスタンプをGET!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateStamp(stampList: List<Stamp>) {
        val adapter = StampListAdapter(activity, stampList, object : StampListAdapter.OnItemClickListener {
            override fun onClick(item: Stamp) {
                Toast.makeText(activity, "label:${item.label} , desc:${item.desc}", Toast.LENGTH_LONG).show()
            }
        })
        rvStampList?.adapter = adapter
    }
}