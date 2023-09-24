package com.batool.calendartest

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.batool.calendartest.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    var calendarItems = ArrayList<CalendarItem>()
    private lateinit var selectedDate: LocalDate
    lateinit var todayDate: LocalDate
    var offDays: ArrayList<String> = ArrayList()

    lateinit var dataBinding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Connect data binding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initClicks()

        //Get current date
        selectedDate = LocalDate.now()
        todayDate = LocalDate.now()

        /**Example "off days" of a certain month,
         **You can call an API to get the days (holidays)
         ** or whatever marked days you want
         */
        offDays.add("5")
        offDays.add("11")
        offDays.add("23")
        offDays.add("27")
        offDays.add("26")

        /**Set the month view after defining (selected date, today date, offdays)*/
        setMonthView()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initClicks() {
        with(dataBinding) {
            nextMonth.setOnClickListener {
                selectedDate = selectedDate.plusMonths(1)
                setMonthView()
            }
            previousMonth.setOnClickListener {
                selectedDate = selectedDate.minusMonths(1)
                setMonthView()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        dataBinding.monthTitle.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)

        calendarItems.clear()

        for (item in daysInMonth) {
            val isToday = item == todayDate.dayOfMonth.toString()
                    && todayDate.monthValue == selectedDate.monthValue
                    && todayDate.year == selectedDate.year
            val isDayOff = offDays.contains(item)

            val calendarItem = CalendarItem(item, selectedDate, isDayOff, isToday)
            calendarItems.add(calendarItem)
        }

        dataBinding.calendarRecycler.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 7)
            adapter = CalendarAdapter {
                //Click on view
            }.apply {
                addItems(calendarItems)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth: LocalDate = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                if (i <= 30) {
                    daysInMonthArray.add("")
                }
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }

        if (dayOfWeek == 7) {
            for (i in 0..6) {
                daysInMonthArray.removeAt(0)
            }
        }
        return daysInMonthArray
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }
}