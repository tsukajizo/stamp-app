package net.tsukajizo.stampapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stamp_list_item.view.*

public class StampListAdapter(private val ctx: Context, private val list: ArrayList<String>) : RecyclerView.Adapter<StampListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(ctx)
        return ViewHolder(inflater.inflate(R.layout.stamp_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (list.size > position) {
            holder?.textView?.text = list.get(position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.tv_text

    }
}