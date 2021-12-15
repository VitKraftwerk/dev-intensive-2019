package ru.skillbranch.devintensive.models

import android.net.Uri
import ru.skillbranch.devintensive.utils.Utils
import java.net.URI
import java.net.URL

data class Profile (
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {

    companion object {
        fun validateRepository(value: String): Boolean{
            var repository = value.trim()
            val exclude = listOf("enterprise", "features", "topics", "collections", "trending", "events", "marketplace", "pricing", "nonprofit", "customer-stories", "security", "login", "join")
            val domain = listOf("github.com", "www.github.com")

            if (repository.isEmpty()) return true

            if (repository == "https://" || repository == "http://") return false

            if (!repository.startsWith("https://") && !repository.startsWith("http://")) {
                repository = "http://$repository"
            }

            val url = URI.create(repository) ?: return false

            if (!domain.contains(url.host)) return false

            val paths = url.path.split('/').toList().filter { x -> x.isNotEmpty() }

            if(paths.size != 1) return false

            if(exclude.contains(paths[0].toLowerCase())) return false

            return true
        }
    }

    val rank: String = "Junior Android Developer"

    val nickName: String
        get() = Utils.transliteration("${this.firstName.trim()} ${this.lastName.trim()}", "_")

    fun toMap(): Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )

}
