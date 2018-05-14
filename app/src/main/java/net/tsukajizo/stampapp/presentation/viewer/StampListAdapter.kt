package net.tsukajizo.stampapp.presentation.viewer

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.stamp_list_item.view.*
import net.tsukajizo.stampapp.App
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.data.Stamp
import javax.inject.Inject


public class StampListAdapter @Inject constructor(private val app: App) : RecyclerView.Adapter<StampListAdapter.ViewHolder>() {

    var list: List<Stamp>? = null
    var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(app.applicationContext)
        return ViewHolder(inflater.inflate(R.layout.stamp_list_item, parent, false))
    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onViewRecycled(viewHolder: ViewHolder) {
        Glide.with(app.applicationContext).clear(viewHolder.imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (itemCount > position) {
            val stamp = list!![position]
            if (stamp.isGathered) {
                val path = list?.get(position)?.getStampPath(app.applicationContext)
                if (holder?.imageView != null) {
                    Glide.with(app.applicationContext).load(Uri.parse(path))
                            .into(holder.imageView)
                }
                holder?.view?.setOnClickListener({ itemClickListener?.onClick(stamp) })
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView
        val imageView = itemView.iv_stamp!!

    }

    interface OnItemClickListener {
        fun onClick(item: Stamp)
    }
}