package com.saint.lib.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saint.lib.loading.LoadingHelper.Adapter
import com.saint.lib.loading.LoadingHelper.ViewHolder
import io.supercharge.shimmerlayout.ShimmerLayout

/**
 * @author Saint  2020/8/20
 */
class SkeletonListAdapter() : Adapter<ViewHolder>() {
    var layoutId = R.layout.loading_layout_skeleton_recycler_item
    var itemCount = 3
    var orientation = RecyclerView.VERTICAL

    constructor(@LayoutRes layoutId: Int) : this() {
        this.layoutId = layoutId
    }

    constructor(@LayoutRes layoutId: Int, itemCount: Int) : this() {
        this.layoutId = layoutId
        this.itemCount = itemCount
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val recyclerView = RecyclerView(parent.context)
        return ViewHolder(recyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        val recyclerView = holder.rootView as RecyclerView
        val layoutManager = recyclerView.layoutManager
        if (layoutManager == null) {
            recyclerView.layoutManager = object : LinearLayoutManager(recyclerView.context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        }
        recyclerView.adapter = ShimmerListAdapter(itemCount, layoutId)
    }

}

class ShimmerListAdapter(private val count: Int, private val itemLayoutId: Int) :
    RecyclerView.Adapter<ShimmerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerListViewHolder {
        return ShimmerListViewHolder(LayoutInflater.from(parent.context), parent, itemLayoutId)
    }

    override fun onBindViewHolder(holder: ShimmerListViewHolder, position: Int) {
        val layout = holder.itemView as ShimmerLayout
        layout.setShimmerAnimationDuration(1500)
        layout.setShimmerAngle(20)
        layout.setShimmerColor(
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.skeleton_shimmer
            )
        )
        layout.startShimmerAnimation()
    }

    override fun getItemCount(): Int {
        return count
    }

}

class ShimmerListViewHolder(inflater: LayoutInflater, parent: ViewGroup, innerViewResId: Int) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.loading_layout_shimmer, parent, false)) {
    init {
        val layout = itemView as ViewGroup
        val view = inflater.inflate(innerViewResId, layout, false)
//        val lp = view.layoutParams
//        if (lp != null) {
//            layout.layoutParams = lp
//        }
        layout.addView(view)
    }
}


