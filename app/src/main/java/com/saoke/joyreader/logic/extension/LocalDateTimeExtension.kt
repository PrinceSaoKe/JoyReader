package com.saoke.joyreader.logic.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.format(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return formatter.format(this)
}