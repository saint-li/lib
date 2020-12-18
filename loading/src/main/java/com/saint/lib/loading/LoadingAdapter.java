

package com.saint.lib.loading;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.saint.lib.util.GlideApp;


/**
 * @author Saint
 */
public class LoadingAdapter extends LoadingHelper.Adapter<LoadingHelper.ViewHolder> {

    private int height = ViewGroup.LayoutParams.MATCH_PARENT;

    public LoadingAdapter() {
    }

    public LoadingAdapter(int height) {
        this.height = height;
    }

    @NonNull
    @Override
    public LoadingHelper.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LoadingHelper.ViewHolder(inflater.inflate(R.layout.loading_layout_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoadingHelper.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.getRootView().getLayoutParams();
        layoutParams.height = height;
        holder.getRootView().setLayoutParams(layoutParams);
        ImageView ivLoading = holder.getRootView().findViewById(R.id.img_loading);
        GlideApp.with(holder.getRootView().getContext())
                .asGif()
                .load(R.mipmap.loading_loading)
                .into(ivLoading);
    }
}
