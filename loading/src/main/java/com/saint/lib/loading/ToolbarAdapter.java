

package com.saint.lib.loading;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;


import com.saint.lib.view.MyActionBar;

import org.jetbrains.annotations.NotNull;

/**
 * @author Dylan Cai
 */
public class ToolbarAdapter extends LoadingHelper.Adapter<ToolbarAdapter.ViewHolder> {

    private String title;
    private View.OnClickListener onBack;
    private View.OnClickListener onRight;
    private int colorBar;
    private int ivLeft, ivRight, tvRightStr;

    public ToolbarAdapter(String title, View.OnClickListener onBack) {
        this.title = title;
        this.onBack = onBack;
    }

    public ToolbarAdapter(String title, @IdRes int ivLeft, View.OnClickListener onBack) {
        this.title = title;
        this.onBack = onBack;
        this.ivLeft = ivLeft;
    }

    public ToolbarAdapter(String title, @IdRes int ivLeft,
                          View.OnClickListener onBack,
                          @IdRes int ivRight, View.OnClickListener onRight) {
        this.title = title;
        this.onBack = onBack;
        this.ivLeft = ivLeft;
        this.onRight = onRight;
        this.ivRight = ivRight;
    }

    public ToolbarAdapter(String title, @ColorRes int barColor, View.OnClickListener onBack,
                          @StringRes int tvRightStr,
                          @DrawableRes int rightRes, View.OnClickListener onRight) {
        this.title = title;
        this.onBack = onBack;
        this.onRight = onRight;
        this.tvRightStr = tvRightStr;
        this.ivRight = rightRes;
        this.colorBar = barColor;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyDataSetChanged();
    }

    public void setTitleBg(@ColorInt int color) {
        this.colorBar = color;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull LayoutInflater inflater,
                                         @NotNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.laoding_layout_toolbar,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder) {
        if (colorBar > 0) {
            holder.toolbar.setTitleBarBG(colorBar);
        }
        if (!TextUtils.isEmpty(title)) {
            holder.toolbar.setTitle(title);
        }

        if (ivLeft > 0) {
            holder.toolbar.setLeftListener(ivLeft, onBack);
        } else {
            holder.toolbar.setLeftListener(onBack);
        }

        if (ivRight > 0 && onRight != null) {
            holder.toolbar.setRightListener(ivRight, onRight);
        }

        if (tvRightStr > 0 && onRight != null) {
            holder.toolbar.setRightTextListener(tvRightStr, onRight);
        }

    }

    static class ViewHolder extends LoadingHelper.ViewHolder {

        private MyActionBar toolbar;

        ViewHolder(@NonNull View rootView) {
            super(rootView);
            toolbar = (MyActionBar) rootView;
        }

        private Activity getActivity() {
            return (Activity) getRootView().getContext();
        }
    }
}
