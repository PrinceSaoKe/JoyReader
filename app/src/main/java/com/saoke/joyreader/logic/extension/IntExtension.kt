package com.saoke.joyreader.logic.extension

import android.content.Context
import kotlin.math.roundToInt

fun Int.dpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density  // 像素密度
    return (this * density).roundToInt()
}