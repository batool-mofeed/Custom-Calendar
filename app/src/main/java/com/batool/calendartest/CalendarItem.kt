package com.batool.calendartest

import java.time.LocalDate

/**
 * Created By Batool Mofeed on 9/21/2023.
 **/
data class CalendarItem(
    var day: String = "",
    var localDate: String,
    var holiday: Boolean = false,
    var today: Boolean = false,
    var events: List<String> = emptyList()
)