package com.goplay.home.utils

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ItemListDecoration : RecyclerView.ItemDecoration() {
    private val PADDING_IN_DIPS = 8
    private var mPadding = 0
    private var orientation = LinearLayoutManager.VERTICAL

    fun itemListDecoration(@NonNull context: Context) {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        mPadding =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                PADDING_IN_DIPS.toFloat(),
                metrics
            ).toInt()
    }

    fun orientationStyle(orientate: Int){
        orientation = orientate
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        if (orientation == LinearLayoutManager.VERTICAL) {
            if (itemPosition == 0) {
                outRect.top = mPadding
            }
            val adapter = parent.adapter
            if (adapter != null && itemPosition == adapter.itemCount - 1) {
                outRect.bottom = mPadding
            }
        } else if (orientation == LinearLayoutManager.HORIZONTAL) {
            if (itemPosition == 0) {
                outRect.left = mPadding
            }
            val adapter = parent.adapter
            if (adapter != null && itemPosition == adapter.itemCount - 1) {
                outRect.right = mPadding
            }
        } else {
            outRect.top = mPadding
            outRect.left = mPadding
            outRect.right = mPadding
            outRect.bottom = mPadding
        }
    }
}