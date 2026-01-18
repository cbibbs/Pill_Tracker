package net.bibbs.pilltracker

import java.util.Calendar
import java.util.Date

data class Pill(
    val name: String,
    val intervalHours: Int? = null,
    val scheduledTimes: List<Pair<Int, Int>>? = null, // List of (Hour, Minute) in 24h format
    val history: List<Date> = emptyList()
) {
    val lastTaken: Date?
        get() = history.lastOrNull()

    val nextTime: Date?
        get() {
            if (intervalHours != null) {
                return lastTaken?.let { Date(it.time + (intervalHours * 60 * 60 * 1000L)) }
            } else if (scheduledTimes != null && scheduledTimes.isNotEmpty()) {
                val now = Calendar.getInstance()
                val sortedTimes = scheduledTimes.sortedWith(compareBy({ it.first }, { it.second }))
                
                // Find the next scheduled time for today
                for (time in sortedTimes) {
                    val scheduledToday = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, time.first)
                        set(Calendar.MINUTE, time.second)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }
                    if (scheduledToday.after(now)) {
                        return scheduledToday.time
                    }
                }
                
                // If no more times today, return the first time tomorrow
                val firstTime = sortedTimes.first()
                return Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, 1)
                    set(Calendar.HOUR_OF_DAY, firstTime.first)
                    set(Calendar.MINUTE, firstTime.second)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }.time
            }
            return null
        }
}