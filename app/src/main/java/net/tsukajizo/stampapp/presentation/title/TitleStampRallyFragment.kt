package net.tsukajizo.stampapp.presentation.title

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_title_stamp_rally.view.*
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.data.Stamp
import net.tsukajizo.stampapp.task.ReadStampTask
import net.tsukajizo.stampapp.task.TaskListener
import javax.inject.Inject

class TitleStampRallyFragment : Fragment() {

    @Inject
    lateinit var readStampTask: ReadStampTask

    companion object {
        fun newInstance(): TitleStampRallyFragment {
            val fragment = TitleStampRallyFragment()
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
        val view = inflater.inflate(R.layout.fragment_title_stamp_rally, container, false)

        view.btn_complete.setOnClickListener({
            Toast.makeText(activity, "おめでとうございます!", Toast.LENGTH_SHORT).show()
        })

        readStampTask.setListener(object : TaskListener<List<Stamp>> {
            override fun onSuccess(result: List<Stamp>) {
                val isComplete = result.none({ stamp -> !stamp.isGathered })
                if (isComplete) {
                    view.ll_normal_title_view.visibility = View.GONE
                    view.ll_collection_complete_view.visibility = View.VISIBLE
                } else {
                    view.ll_normal_title_view.visibility = View.VISIBLE
                    view.ll_collection_complete_view.visibility = View.GONE
                }
                super.onSuccess(result)
            }
        })
        readStampTask.execute()
        return view
    }
}