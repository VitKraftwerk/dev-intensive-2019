package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun test_fullName() {
        println(Utils.parseFullName(null)) //null null
        println(Utils.parseFullName("")) //null null
        println(Utils.parseFullName(" ")) //null null
        println(Utils.parseFullName("John")) //John null
    }

    @Test
    fun test_date_format() {
        println(Date().format()) //14:00:00 27.06.19
        println(Date().format("HH:mm")) //14:00
    }

    @Test
    fun test_date_add() {
        println(Date())
        println(Date().add(2, TimeUnits.SECOND)) //14:00:00 27.06.19
        println(Date().add(-4, TimeUnits.DAY)) //14:00
    }

    @Test
    fun test_toInitials() {
        println(Utils.toInitials("john", "doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
    }

    @Test
    fun test_humanizeDiff() {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }

    @Test
    fun test_plural() {
        println(TimeUnits.SECOND.plural(1)) //1 секунду
        println(TimeUnits.MINUTE.plural(4)) //4 минуты
        println(TimeUnits.HOUR.plural(19))//19 часов
        println(TimeUnits.DAY.plural(222)) //222 дня
    }

    @Test
    fun test_translit(){
        println(Utils.transliteration("Женя Стереотипов")) //Zhenya Stereotipov
        println(Utils.transliteration("Amazing Петр","_")) //Amazing_Petr
    }

    @Test
    fun test_builder(){
        val user = User.Builder().id("s")
            .firstName("s")
            .lastName("s")
            .avatar("s")
            .rating(1)
            .respect(2)
            .lastVisit(Date())
            .isOnline(true)
            .build()

        println(user)
    }

    @Test
    fun test_trancate(){
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()) //Bender Bending R...
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15)) //Bender Bending...
        println("A     ".truncate(3)) //A
    }

    @Test
    fun test_html(){
        println("<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml()) //Образовательное IT-сообщество Skill Branch
        println("<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml()) //Образовательное IT-сообщество Skill Branch
    }

    @Test
    fun test_validateRepository() {
        val tests = listOf(
            "https://github.com/johnDoe", //валиден
            "https://www.github.com/johnDoe", //валиден
            "www.github.com/johnDoe", //валиден
            "github.com/johnDoe", //валиден
            "https://anyDomain.github.com/johnDoe", //невалиден
            "https://github.com/", //невалиден
            "https://github.com", //невалиден
            "https://github.com/johnDoe/tree", //невалиден
            "https://github.com/johnDoe/tree/something", //невалиден
            "https://github.com/enterprise", //невалиден
            "https://github.com/pricing", //невалиден
            "https://github.com/join" //невалиден
        )

        for (test in tests) {
            print("$test - ")
            println(Profile.validateRepository(test))
        }
    }
}
