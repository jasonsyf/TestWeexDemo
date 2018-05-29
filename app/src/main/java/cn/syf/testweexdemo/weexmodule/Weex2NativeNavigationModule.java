package cn.syf.testweexdemo.weexmodule;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.appfram.navigator.WXNavigatorModule;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.syf.testweexdemo.TestSendParamsActivity;

/**
 * @author : Jason_Sunyf
 * @date : 2018年05月25日13时55分
 * E-mail  : jason_sunyf@163.com
 */
public class Weex2NativeNavigationModule extends WXNavigatorModule {

    /**
     * 后期加ARoute 可以定义path  传递path  目前只能固定跳转
     */
    @JSMethod()
    public void startActivity() {
        if (mWXSDKInstance.getUIContext() instanceof Activity) {
            Intent intent = new Intent();
            intent.setClass(mWXSDKInstance.getUIContext(), TestSendParamsActivity.class);
            mWXSDKInstance.getUIContext().startActivity(intent);
        }
    }

    @JSMethod()
    public void startActivity(String path,Object params) {
//            ARouter.getInstance.build(path).with(params).navigate();
    }

    /**
     * 结束当前Weex页面所在的activity
     */
    @JSMethod()
    public void finishActivity() {
        if (mWXSDKInstance.getUIContext() instanceof Activity) {
            ((Activity) mWXSDKInstance.getUIContext()).finish();
        }

    }

}
