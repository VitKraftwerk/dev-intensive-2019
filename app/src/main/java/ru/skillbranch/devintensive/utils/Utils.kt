package ru.skillbranch.devintensive.utils

object Utils {
    private fun checkNull(str: String?): String?{
        return if(str.isNullOrBlank()) null else str
    }
    private fun checkEmpty(str: String?): String{
        return if (str != null) str else ""
    }
//    private fun getInitial(str: String?): String{
//        return str?.
//    }
    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")

        val firstName = checkNull(parts?.getOrNull(0))
        val lastName = checkNull(parts?.getOrNull(1))

        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return ""
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return checkNull(checkEmpty(firstName?.take(1)) + checkEmpty(lastName?.take(1)))?.toUpperCase()
    }
}