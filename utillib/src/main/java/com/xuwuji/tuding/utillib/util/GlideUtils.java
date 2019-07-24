package com.xuwuji.tuding.utillib.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.xuwuji.tuding.utillib.AppConfig;
import com.xuwuji.tuding.utillib.GlideApp;
import com.xuwuji.tuding.utillib.R;

/**
 * Created by Administrator on 2017/12/4.
 */

public class GlideUtils {
    private static RequestOptions option = new RequestOptions()
            .override(100, 100)
            .placeholder(R.drawable.img_head)
            .error(R.drawable.img_head)
            .circleCrop();

    //设置图片圆角角度
    private static RoundedCorners roundedCorners = new RoundedCorners(AppUtil.getDimen(R.dimen.dp_4));
    //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
    private static RequestOptions options = RequestOptions
            .bitmapTransform(roundedCorners)
            .placeholder(R.drawable.img_head)
            .error(R.drawable.img_head);


//    private static RequestOptions createOption(int errorId) {
//        return new RequestOptions()
//                .placeholder(errorId)
//                .error(errorId);
//
//    }

    public static void loadIMg(Activity act, String imgUrl, ImageView imageView) {
        GlideApp.with(act).load(imgUrl)
//                .apply(option)
                .into(imageView);
    }

    public static void loadIMg(Activity act, String imgUrl, ImageView imageView, RequestOptions option) {
        GlideApp.with(act).load(imgUrl)
                .apply(option)
                .into(imageView);
    }

    public static void loadIMg(Fragment frag, String imgUrl, ImageView imageView, RequestOptions option) {
        GlideApp.with(frag).load(imgUrl)
                .apply(option)
                .into(imageView);
    }


    public static void loadIMg(Activity act, int imgId, ImageView imageView) {
        Glide.with(act).load(imgId)
                .into(imageView);
    }

    public static void loadBaseIMg(String imgUrl, ImageView imageView, RequestOptions option) {
        Glide.with(AppConfig.getApp())
                .load(imgUrl)
                .apply(option)
                .into(imageView);
    }

//    public static void setImgForViewBackgroud(Context context, String imgUrl, SimpleTarget simpleTarget) {
//        Glide.with(context).load(imgUrl).asBitmap().into(simpleTarget);
//    }
//
//    public static void setImgForViewBackgroud(Context context, int imgRes, SimpleTarget simpleTarget) {
//        Glide.with(context).load(imgRes).asBitmap().into(simpleTarget);
//    }

    public static void loadHead(Context context, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url)
                .apply(option)
                .circleCrop()
                .into(iv);
    }

    public static void loadRound(Context context, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url + "-80x80.jpg")
                .apply(options)
                .into(iv);
    }

}