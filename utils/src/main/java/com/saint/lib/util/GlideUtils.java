package com.saint.lib.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by Administrator on 2017/12/4.
 */

public class GlideUtils {
    //设置图片圆角角度
    private static RoundedCorners roundedCorners = new RoundedCorners(DisplayUtil.INSTANCE.dpToPx(4, UtilConfig.getApp()));
    //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
    private static RequestOptions options = RequestOptions
            .bitmapTransform(roundedCorners)
            .placeholder(R.mipmap.util_img_loading)
            .error(R.mipmap.util_img_failed);

    public static void loadIMg(Context context, String imgUrl, ImageView imageView) {
        GlideApp.with(context).load(imgUrl)
                .into(imageView);
    }

    public static void loadIMg(Activity act, String imgUrl, ImageView imageView) {
        GlideApp.with(act).load(imgUrl)
                .into(imageView);
    }

    public static void loadIMg(Activity act, String imgUrl, ImageView imageView, RequestOptions option) {
        GlideApp.with(act).load(imgUrl)
                .into(imageView);
    }

    public static void loadIMg(Fragment frag, String imgUrl, ImageView imageView, RequestOptions option) {
        GlideApp.with(frag).load(imgUrl)
                .into(imageView);
    }


    public static void loadIMg(Activity act, int imgId, ImageView imageView) {
        Glide.with(act).load(imgId)
                .into(imageView);
    }

    public static void loadBaseIMg(String imgUrl, ImageView imageView, RequestOptions option) {
        Glide.with(UtilConfig.getApp())
                .load(imgUrl)
                .apply(option)
                .into(imageView);
    }

    public static void loadRound(Context context, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url + "-80x80.jpg")
                .apply(options)
                .into(iv);
    }

    public static void loadRound(Activity context, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url + "-80x80.jpg")
                .apply(options)
                .into(iv);
    }

    public static void loadRound(Fragment context, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url + "-80x80.jpg")
                .apply(options)
                .into(iv);
    }

    public static void loadRound(Fragment context, RequestOptions options, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url)
                .apply(options)
                .into(iv);
    }

    public static void loadImg(Context context, RequestOptions options, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url)
                .apply(options)
                .into(iv);
    }

    public static void loadRound(Fragment context, RequestOptions options, int drawableResId, ImageView iv) {
        GlideApp.with(context)
                .load(drawableResId)
                .apply(options)
                .into(iv);
    }

    public static void loadRound(Context context, RequestOptions options, int drawableResId, ImageView iv) {
        GlideApp.with(context)
                .load(drawableResId)
                .apply(options)
                .into(iv);
    }

    public static void loadBitmap(Fragment context, String avatar, RequestOptions option, SimpleTarget<Bitmap> target) {
        GlideApp.with(context)
                .asBitmap()
                .apply(option)
                .load(avatar)
                .into(target);
    }

    public static void loadBitmap(Context context, String avatar, RequestOptions option, SimpleTarget<Bitmap> target) {
        GlideApp.with(context)
                .asBitmap()
                .apply(option)
                .load(avatar)
                .into(target);
    }

    public static void loadDrawable(Context context, Uri uri, SimpleTarget<Drawable> simpleTarget) {
        GlideApp.with(context)
                .load(uri)
                .into(simpleTarget);
    }

}
