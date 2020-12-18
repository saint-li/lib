package com.saint.lib.util

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

/**
 * @author Saint  2020/12/17.
 * DESC：
 */
object DisplayUtil {
    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).roundToInt()
    }

    fun spToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            dp,
            context.resources.displayMetrics
        ).roundToInt()
    }
}