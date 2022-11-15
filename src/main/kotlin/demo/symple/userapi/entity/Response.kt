package demo.symple.userapi.entity

import java.time.LocalDateTime

class Response(
    val code: Int,
    val message: String,
    val eventTime: LocalDateTime = LocalDateTime.now(),
)
