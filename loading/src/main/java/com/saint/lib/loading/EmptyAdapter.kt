
package com.saint.lib.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

/**
 * @author Saint
 */
class EmptyAdapter : LoadingHelper.Adapter<LoadingHelper.ViewHolder>() {
    var tips: String? = null

    @DrawableRes
    var tipsIcon = 0

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
            : LoadingHelper.ViewHolder {
        return LoadingHelper.ViewHolder(
            inflater.inflate(R.layout.loading_layout_empty, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LoadingHelper.ViewHolder) {
        if (tips != null && tips!!.isNotEmpty()) {
            val tvTips = holder.rootView.findViewById<TextView>(R.id.tv_empty_tips)
            tvTips.text = tips
        }
        if (tipsIcon > 0) {
            val ivTips = holder.rootView.findViewById<ImageView>(R.id.iv_empty_tips)
            ivTips.setImageResource(tipsIcon)
        }
    }

    fun refreshTips(tips: String, @DrawableRes tipsIcon: Int) {
        this.tips = tips
        this.tipsIcon = tipsIcon
        notifyDataSetChanged()
    }

}