package net.tsukajizo.stampapp.presentation.title

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_title_stamp_rally.view.*
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.task.ReadStampTask
import javax.inject.Inject

class TitleStampRallyFragment : DaggerFragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_title_stamp_rally, container, false)

        view.btn_complete.setOnClickListener({
            AlertDialog.Builder(activity).apply {
                setTitle("コンプリート！")
                setMessage("おめでとうございます！")
                setNegativeButton("OK", null)
            }.create().show()
        })


        readStampTask.execute({
            val isComplete = it.none({ stamp -> !stamp.isGathered })
            if (isComplete) {
                view.ll_normal_title_view.visibility = View.GONE
                view.ll_collection_complete_view.visibility = View.VISIBLE
            } else {
                view.ll_normal_title_view.visibility = View.VISIBLE
                view.ll_collection_complete_view.visibility = View.GONE
            }
        }, {
            Toast.makeText(activity, "エラー:${it.message}", Toast.LENGTH_SHORT).show()
            // TODO エラー処理を書く
        }, Unit)
        return view
    }
}