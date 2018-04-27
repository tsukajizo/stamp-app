package net.tsukajizo.stampapp.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stamp_list_item.view.*
import net.tsukajizo.stampapp.R
import net.tsukajizo.stampapp.data.Stamp

public class StampListAdapter(private val ctx: Context, private val list: List<Stamp>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<StampListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(ctx)
        return ViewHolder(inflater.inflate(R.layout.stamp_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (list.size > position) {
            val stamp = list[position]
            if (stamp.isGathered) {
                val drawable = list.get(position).getStampDrawable(ctx)
                holder?.imageView?.setImageDrawable(drawable)
                holder?.view?.setOnClickListener({ itemClickListener.onClick(stamp) })
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