package net.bibbs.pilltracker

import java.util.Date

data class Pill(
    val name: String,
    val intervalHours: Int,
    val history: List<Date> = emptyList()
) {
    val lastTaken: Date?
        get() = history.lastOrNull()

    val nextTime: Date?
        get() = lastTaken?.let { Date(it.time + (intervalHours * 60 * 60 * 1000L)) }
}