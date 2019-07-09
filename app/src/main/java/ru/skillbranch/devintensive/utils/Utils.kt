package ru.skillbranch.devintensive.utils

object Utils {
    private fun checkNull(str: String?): String?{
        return if(str.isNullOrBlank()) null else str
    }

    private fun checkEmpty(str: String?): String{
        return if (str != null) str else ""
    }

    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")

        val firstName = checkNull(parts?.getOrNull(0))
        val lastName = checkNull(parts?.getOrNull(1))

        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result: String = ""
        for (char in payload) {
            result += translite("$char", divider)
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return checkNull(checkEmpty(firstName?.take(1)) + checkEmpty(lastName?.take(1)))?.toUpperCase()
    }

    private fun translite(payload: String, divider: String): String?{
        return when (payload){
            "А" -> "A"
            "Б" -> "B"
            "В" -> "V"
            "Г" -> "G"
            "Д" -> "D"
            "Е" -> "E"
            "Ё" -> "E"
            "Ж" -> "Zh"
            "З" -> "Z"
            "И" -> "I"
            "Й" -> "I"
            "К" -> "K"
            "Л" -> "L"
            "М" -> "M"
            "Н" -> "N"
            "О" -> "O"
            "П" -> "P"
            "Р" -> "R"
            "С" -> "S"
            "Т" -> "T"
            "У" -> "U"
            "Ф" -> "F"
            "Х" -> "H"
            "Ц" -> "C"
            "Ч" -> "Ch"
            "Ш" -> "Sh"
            "Щ" -> "Sh'"
            "Ъ" -> ""
            "Ы" -> "I"
            "Ь" -> ""
            "Э" -> "E"
            "Ю" -> "Yu"
            "Я" -> "Ya"
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "e"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "i"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "t"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "h"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "sh'"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "yu"
            "я" -> "ya"
            " " -> divider
            else -> payload
        }
    }
}