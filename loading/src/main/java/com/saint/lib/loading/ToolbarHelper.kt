
package com.saint.lib.loading

import android.app.Activity
import android.view.View.OnClickListener
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @author Dylan Cai
 */
object ToolbarHelper {
    fun setToolbar(activity: Activity, title: String?, leftClick: OnClickListener): LoadingHelper {
        val loadingHelper = LoadingHelper(activity)
        loadingHelper.register(ViewType.TITLE, ToolbarAdapter(title, leftClick))
        loadingHelper.setDecorHeader(ViewType.TITLE)
        return loadingHelper
    }

    fun setToolbar(
        activity: Activity,
        title: String, @ColorRes barColor: Int,
        onLeftClick: OnClickListener,
        @StringRes rightText: Int,
        @DrawableRes rightRes: Int,
        onRightClick: OnClickListener
    ): LoadingHelper {
        val loadingHelper = LoadingHelper(activity)
        loadingHelper.register(
            ViewType.TITLE,
            ToolbarAdapter(title, barColor, onLeftClick, rightText, rightRes, onRightClick)
        )
        loadingHelper.setDecorHeader(ViewType.TITLE)
        return loadingHelper
    }

}