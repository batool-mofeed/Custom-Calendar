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
    var offDays: ArrayList<Event> = ArrayList()

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
        offDays.add(
            Event(
                todayDate.plusDays(5).toString(),
                listOf("Dublin Tech Summit", "Black Hat USA")
            )
        )
        offDays.add(Event(todayDate.minusDays(1).toString(), listOf("Mobile World Congress")))
        offDays.add(Event(todayDate.plusDays(2).toString(), listOf("Viva Technology")))
        offDays.add(Event(todayDate.plusDays(16).toString(), listOf("AI & Big Data Expo")))
        offDays.add(
            Event(
                todayDate.minusDays(10).toString(),
                listOf("Web Summit", "Future Sustainability Summit")
            )
        )

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
        // Set the month title
        val monthTitleText = formattedDateFromDate(selectedDate, "MMMM yyyy")
        dataBinding.monthTitle.text = monthTitleText

        // Get the days in the selected month
        val daysInMonth = daysInMonthArray(selectedDate)

        // Clear the calendar items
        calendarItems.clear()

        for (item in daysInMonth) {
            // Check if the current day is today
            val isToday = item == todayDate.dayOfMonth.toString()
                    && todayDate.monthValue == selectedDate.monthValue
                    && todayDate.year == selectedDate.year

            // Check if the current day is a day off
            val isDayOff = offDays.any { LocalDate.parse(it.date).dayOfMonth.toString() == item }

            // Find the event day if it's a day off
            val foundEventDay = if (isDayOff) offDays.find { LocalDate.parse(it.date).dayOfMonth.toString() == item } else null

            // Check if the day off belongs to the selected month
            val isDayOffInSelectedMonth = isDayOff && selectedDate.monthValue == LocalDate.parse(foundEventDay?.date).monthValue

            // Create a CalendarItem
            val calendarItem = CalendarItem(
                item,
                if (isDayOffInSelectedMonth) foundEventDay!!.date else selectedDate.toString(),
                isDayOffInSelectedMonth,
                isToday,
                if (isDayOffInSelectedMonth) foundEventDay!!.events else emptyList()
            )
            calendarItems.add(calendarItem)
        }

        // Set up the RecyclerView
        dataBinding.calendarRecycler.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 7)
            adapter = CalendarAdapter {
                // Click on view
                initEventsAdapter(it)
            }.apply {
                addItems(calendarItems)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initEventsAdapter(it: CalendarItem) {
        dataBinding.eventDate.text =
            formattedDateFromDate(LocalDate.parse(it.localDate), "dd-MM-yyyy")
        dataBinding.eventsRecycler.apply {
            adapter = EventAdapter().apply {
                addItems(it.events)
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
    private fun formattedDateFromDate(date: LocalDate, pattern: String): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
        return date.format(formatter)
    }


}