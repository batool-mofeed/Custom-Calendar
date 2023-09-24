package com.batool.calendartest

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.batool.calendartest.base.BaseRecyclerViewAdapter
import com.batool.calendartest.base.BaseViewHolder
import com.batool.calendartest.databinding.ItemCalendarBinding

/**
 * Created By Batool Mofeed on 9/19/2023.
 **/
class CalendarAdapter(private val clicked: (CalendarItem) -> Unit) :
    BaseRecyclerViewAdapter<CalendarItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CalendarViewHolder(
            ItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class CalendarViewHolder(
        private val binding: ItemCalendarBinding
    ) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            val comingItem = items[position]
            with(binding) {
                item = comingItem

                root.setOnClickListener {
                    if (comingItem.holiday) {
                        clicked(comingItem)
                    }
                }

                /**
                 * Customize Friday and Saturday
                 */
                if ((position + 1) % 7 == 0 || (position + 2) % 7 == 0) {
                    dayText.setTypeface(null, Typeface.BOLD)
                }

                if (comingItem.day == "") {
                    dayText.isVisible = false
                    rectangle.isVisible = false
                }
            }
        }
    }

}