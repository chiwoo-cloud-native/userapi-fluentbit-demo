package demo.symple.userapi.entity

import java.time.LocalDateTime

class User(
    val name: String,
    val nick: String?,
    val email: String?,
    val age: Int?,
    var createdDts: LocalDateTime?,
)
