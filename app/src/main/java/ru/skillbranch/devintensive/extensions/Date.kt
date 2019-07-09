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

fun TimeUnits.plural(value: Int): String{
    return "$value " + getWord(value.toLong(), this)
}

fun TimeUnits.plural(value: Long): String{
    return "$value " + getWord(value, this)
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

private fun getWord(value: Long, unit: TimeUnits): String {
    return when(value) {
        1L, 21L, 31L, 41L, 51L, 61L, 71L, 81L, 91L -> when (unit) {
            TimeUnits.SECOND -> "секунду"
            TimeUnits.MINUTE -> "минуту"
            TimeUnits.HOUR -> "час"
            TimeUnits.DAY -> "день"
        }
        in 2..4, in 22..24, in 32..34, in 42..44, in 52..54, in 62..64, in 72..74, in 82..84, in 92..94 -> when (unit) {
            TimeUnits.SECOND -> "секунды"
            TimeUnits.MINUTE -> "минуты"
            TimeUnits.HOUR -> "часа"
            TimeUnits.DAY -> "дня"
        }
        in 5..20, in 25..30, in 35..40, in 55..60, in 65..70, in 75..80, in 85..90, in 95..100 -> when (unit) {
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
        }
        in 101..Long.MAX_VALUE -> getWord(value % 100, unit)
        else -> "-"
    }
}


fun Date.humanizeDiff(): String {

    val diff = (Date().time - this.time)

    return when(diff){
        in Long.MIN_VALUE..-360*DAY -> "более чем через год"
        in -360*DAY..-26*HOUR -> "через ${TimeUnits.DAY.plural(-diff/DAY)}"
        in -26*HOUR..-22*HOUR -> "через день"
        in -22*HOUR..-75*MINUTE -> "через ${TimeUnits.HOUR.plural(-diff/HOUR)}"
        in -75*MINUTE..-45*MINUTE -> "через час"
        in -45*MINUTE..-75*SECOND -> "через ${TimeUnits.MINUTE.plural(-diff/MINUTE)}"
        in -75*SECOND..-45*SECOND -> "через минуту"
        in -45*SECOND..-1*SECOND -> "через несколько секунд"
        in -1*SECOND..0*SECOND -> "вот вот"
        in 0*SECOND..1*SECOND -> "только что"
        in 1*SECOND..45*SECOND -> "несколько секунд назад"
        in 45*SECOND..75*SECOND -> "минуту назад"
        in 75*SECOND..45*MINUTE -> "${TimeUnits.MINUTE.plural(diff/MINUTE)} назад"
        in 45*MINUTE..75*MINUTE -> "час назад"
        in 75*MINUTE..22*HOUR -> "${TimeUnits.HOUR.plural(diff/HOUR)} назад"
        in 22*HOUR..26*HOUR -> "день назад"
        in 26*HOUR..360*DAY -> "${TimeUnits.DAY.plural(diff/DAY)} назад"
        else -> "более года назад"
    }
}