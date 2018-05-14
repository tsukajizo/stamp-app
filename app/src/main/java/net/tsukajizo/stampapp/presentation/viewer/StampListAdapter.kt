package net.tsukajizo.stampapp.presentation.viewer

import android.content.Context
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

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(app.applicationContext).inflate(R.layout.stamp_list_item, parent, false))


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onViewRecycled(viewHolder: ViewHolder) =
            viewHolder.recycle(app.applicationContext)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(app.applicationContext, list!![position], itemClickListener!!)


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView
        val imageView = itemView.iv_stamp!!

        fun bind(context: Context, stamp: Stamp, itemClickListener: OnItemClickListener) {
            if (stamp.isGathered) {
                val path = stamp.getStampPath(context)
                Glide.with(context).load(Uri.parse(path))
                        .into(imageView)
                view.setOnClickListener({ itemClickListener?.onClick(stamp) })
            }
        }

        fun recycle(context: Context) {
            Glide.with(context).clear(imageView)
        }
    }

    interface OnItemClickListener {
        fun onClick(item: Stamp)
    }
}