package net.tsukajizo.stampapp.presentation.viewer

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class StampListItemDecoration(val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.left = space
        outRect?.right = space
        outRect?.bottom = space
        outRect?.top = space
        outRect?.top = space
    }
}