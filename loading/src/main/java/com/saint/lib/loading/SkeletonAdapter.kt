package com.saint.lib.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.ethanhua.skeleton.SkeletonScreen
import com.saint.lib.loading.LoadingHelper.Adapter
import com.saint.lib.loading.LoadingHelper.ViewHolder
import com.saint.lib.util.GlideApp
import io.supercharge.shimmerlayout.ShimmerLayout

/**
 * @author Saint  2020/8/20
 */
class SkeletonAdapter() : Adapter<ViewHolder>() {
    private var skeletonScreen: SkeletonScreen? = null
    var layoutId = R.layout.loading_layout_loading

    constructor(@LayoutRes layoutId: Int) : this() {
        this.layoutId = layoutId
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return if (layoutId == R.layout.loading_layout_loading) {
            ViewHolder(inflater.inflate(layoutId, parent, false))
        } else {
            ShimmerViewHolder(inflater, layoutId)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        if (layoutId == R.layout.loading_layout_loading) {
            val layoutParams = holder.rootView.layoutParams
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            holder.rootView.layoutParams = layoutParams
            val ivLoading = holder.rootView.findViewById<ImageView>(R.id.img_loading)
            GlideApp.with(holder.rootView.context)
                .asGif()
                .load(R.mipmap.loading_loading)
                .into(ivLoading)
        } else {
            val layout = holder.rootView as ShimmerLayout
            layout.startShimmerAnimation()
        }
    }

    fun hide() {
        if (skeletonScreen != null) skeletonScreen!!.hide()
    }

    class ShimmerViewHolder(inflater: LayoutInflater, innerViewResId: Int) :
        ViewHolder(inflater.inflate(R.layout.loading_layout_shimmer, null)) {
        init {
            val layout = rootView as ViewGroup
            val view = inflater.inflate(innerViewResId, layout, false)
            val lp = view.layoutParams
            if (lp != null) {
                layout.layoutParams = lp
            }
            layout.addView(view)
        }
    }
}


