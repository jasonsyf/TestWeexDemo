package cn.syf.testweexdemo;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import cn.syf.testweexdemo.weexadapter.ImageAdapter;
import cn.syf.testweexdemo.weexadapter.WeexOkHttpAdapter;
import cn.syf.testweexdemo.weexmodule.Weex2NativeNavigationModule;

/**
 * @author : Jason_Sunyf
 * @date : 2018年04月12日14时12分
 * E-mail  : jason_sunyf@163.com
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        initDebugEnvironment(true, false, "192.168.1.14");
        InitConfig config=new InitConfig.Builder()
                .setImgAdapter(new ImageAdapter())
                .setHttpAdapter(new WeexOkHttpAdapter())
                .build();
        WXSDKEngine.initialize(this,config);
        try {
            WXSDKEngine.registerModule("Weex2NativeNavigationModule", Weex2NativeNavigationModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }

    }

    public static MyApplication getInstance() {
        return instance;
    }

//    private void initDebugEnvironment(boolean connectable, boolean debuggable, String host) {
//        if (!"DEBUG_SERVER_HOST".equals(host)) {
//            WXEnvironment.sDebugServerConnectable = connectable;
//            WXEnvironment.sRemoteDebugMode = debuggable;
//            WXEnvironment.sRemoteDebugProxyUrl = "ws://" + host + ":8088/debugProxy/native";
//        }
//    }
}
