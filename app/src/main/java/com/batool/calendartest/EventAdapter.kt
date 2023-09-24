package com.batool.calendartest

import android.view.LayoutInflater
import android.view.ViewGroup
import com.batool.calendartest.base.BaseRecyclerViewAdapter
import com.batool.calendartest.base.BaseViewHolder
import com.batool.calendartest.databinding.ItemEventBinding

/**
 * Created By Batool Mofeed on 9/24/2023.
 **/
class EventAdapter() :
    BaseRecyclerViewAdapter<String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class EventViewHolder(
        private val binding: ItemEventBinding
    ) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            val comingItem = items[position]
            with(binding) {

                eventName.text = comingItem
            }
        }
    }

}