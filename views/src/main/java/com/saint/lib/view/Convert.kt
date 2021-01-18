package com.saint.lib.view

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.TypedValue
import kotlin.math.roundToInt

/**
 * @author Saint  2020/12/17.
 * DESC：
 */
object Convert {
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

    fun getStatusBarHeight(context: Context): Int {
        val resourceId: Int = context.resources
            .getIdentifier("status_bar_height", "dimen", "android")
        var height = try {
            context.resources.getDimensionPixelSize(resourceId)
        } catch (e: NotFoundException) {
            dpToPx(24f, context)
        }
        return height
    }
}