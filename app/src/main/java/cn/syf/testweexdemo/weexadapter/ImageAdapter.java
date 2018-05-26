package cn.syf.testweexdemo.weexadapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.IWXBridge;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * @author : Jason_Sunyf
 * @date : 2018年04月12日14时10分
 * E-mail  : jason_sunyf@163.com
 */
public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        if (!"".equals(url)) {
            Glide.with(WXEnvironment.getApplication()).load(url).into(view);
        }
    }

}
