package ru.skillbranch.devintensive.extensions

import android.text.Editable

const val FILLER = "..."

fun String.truncate(size: Int = 16): String{
    return this.take(size).trim() + if (this.trim().length > size) FILLER else ""
}

fun String.stripHtml(): String{
    val re = Regex("\\<[^>]*>")
    val re2 = Regex("\\s{2,}")
    return this.replace(re,"").replace(re2," ")
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)