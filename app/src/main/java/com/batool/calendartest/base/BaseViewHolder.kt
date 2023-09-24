package com.batool.calendartest.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By Batool Mofeed on 9/21/2023.
 **/
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}