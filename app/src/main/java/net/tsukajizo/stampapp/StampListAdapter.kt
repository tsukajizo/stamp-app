package net.tsukajizo.stampapp

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stamp_list_item.view.*

public class StampListAdapter(private val ctx: Context, private val list: TypedArray) : RecyclerView.Adapter<StampListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(ctx)
        return ViewHolder(inflater.inflate(R.layout.stamp_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.length()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (list.length() > position) {

            val drawable = list.getDrawable(position)
            holder?.imageView?.setImageDrawable(drawable)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.iv_stamp

    }
}