package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {

    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(): String {

    val diff = (Date().time - this.time)

    return when(diff){
        in Long.MIN_VALUE..-360*DAY -> "более чем через год"
        in -360*DAY..-26*HOUR -> "через ${-diff/DAY} дней"
        in -26*HOUR..-22*HOUR -> "через день"
        in -22*HOUR..-75*MINUTE -> "через ${-diff/HOUR} часов"
        in -75*MINUTE..-45*MINUTE -> "через час"
        in -45*MINUTE..-75*SECOND -> "через ${-diff/MINUTE} минут"
        in -75*SECOND..-45*SECOND -> "через минуту"
        in -45*SECOND..-1*SECOND -> "через несколько секунд"
        in -1*SECOND..0*SECOND -> "вот вот"
        in 0*SECOND..1*SECOND -> "только что"
        in 1*SECOND..45*SECOND -> "несколько секунд назад"
        in 45*SECOND..75*SECOND -> "минуту назад"
        in 75*SECOND..45*MINUTE -> "${diff/MINUTE} минут назад"
        in 45*MINUTE..75*MINUTE -> "час назад"
        in 75*MINUTE..22*HOUR -> "${diff/HOUR} часов назад"
        in 22*HOUR..26*HOUR -> "день назад"
        in 26*HOUR..360*DAY -> "${diff/DAY} дней назад"
        else -> "более года назад"
    }
}