package com.batool.calendartest.base

import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created By Batool Mofeed on 9/21/2023.
 **/
//base generic recyclerview adapter to handle common functionality
abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {
    protected var items: MutableList<T> = ArrayList()
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = items.size

    fun addItems(items: List<T>) {
        this.items.addAll(items)
        notifyItemRangeInserted(itemCount, items.size - 1)
    }

    fun addItem(item: T) {
        this.items.add(item)
        notifyItemRangeInserted(itemCount, items.size - 1)
    }

    fun removeItem(item: T) {
        val index = this.items.indexOf(item)
        this.items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }
}