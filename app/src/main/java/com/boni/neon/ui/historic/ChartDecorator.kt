package com.boni.neon.ui.historic

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ChartDecorator: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let {
            if(parent.getChildAdapterPosition(view) != it.itemCount - 1) {
                outRect.right = 24
            }
        }
    }
}