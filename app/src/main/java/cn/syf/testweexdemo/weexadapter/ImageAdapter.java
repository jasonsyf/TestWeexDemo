package cn.syf.testweexdemo.weexadapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

import java.util.Optional;

import javax.sql.DataSource;

import cn.syf.testweexdemo.MyApplication;

/**
 * @author : Jason_Sunyf
 * @date : 2018年04月12日14时10分
 * E-mail  : jason_sunyf@163.com
 */
public class ImageAdapter implements IWXImgLoaderAdapter {

    public ImageAdapter() {
    }

    @Override
    public void setImage(final String url, final ImageView view, WXImageQuality quality, final WXImageStrategy strategy) {
        WXSDKManager.getInstance().postOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getInstance(), "加载图片", Toast.LENGTH_SHORT).show();
                if (view == null || view.getLayoutParams() == null) {
                    return;
                }
                if (TextUtils.isEmpty(url)) {
                    view.setImageBitmap(null);
                    return;
                }
                Glide.with(WXEnvironment.getApplication()).load(url).into(view);
                if (url.startsWith("file://")) {
                    return;
                }
                String temp = url;
                if (url.startsWith("//")) {
                    temp = "http:" + url;
                }
                Glide.with(WXEnvironment.getApplication()).load(temp).asBitmap().into(new WeeXImageTarget(strategy, url, view));
            }
        }, 0);
    }

    private class WeeXImageTarget extends SimpleTarget<Bitmap> {

        private WXImageStrategy mWXImageStrategy;
        private String mUrl;
        private ImageView mImageView;

        WeeXImageTarget(WXImageStrategy strategy, String url, ImageView imageView) {
            mWXImageStrategy = strategy;
            mUrl = url;
            mImageView = imageView;
        }

        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            mImageView.setImageBitmap(resource);
            if (mWXImageStrategy != null && mWXImageStrategy.getImageListener() != null) {
                mWXImageStrategy.getImageListener().onImageFinish(mUrl, mImageView, true, null);
            }
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            if (mWXImageStrategy != null && mWXImageStrategy.getImageListener() != null) {
                mWXImageStrategy.getImageListener().onImageFinish(mUrl, mImageView, false, null);
            }
        }
    }
}
