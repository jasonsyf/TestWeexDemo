package cn.syf.testweexdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IWXRenderListener {
    WXSDKInstance mWXSDKInstance;
    String TEST_URL = "http://192.168.1.14:8081/?hot-reload_controller&page=TestLogin.js";
    Intent mIntent;
    private String account, pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getIntent();
        if (mIntent != null) {
            account = mIntent.getStringExtra("account");
            pwd = mIntent.getStringExtra("pwd");
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);

        Map<String, Object> options = new HashMap<>();
//        options.put(WXSDKInstance.BUNDLE_URL, TEST_URL);
        options.put("abc", account);
        options.put("bcd", pwd);
//        mWXSDKInstance.renderByUrl("WXSample",TEST_URL,options,null,WXRenderStrategy.APPEND_ONCE);
//        mWXSDKInstance.render("测试啊啊啊",null,WXFileUtils.loadAsset("HelloWorld.js", this));
        mWXSDKInstance.render(MainActivity.class.getName(), WXFileUtils.loadAsset("TestLogin.weex.js",
                MainActivity.this), options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {

        setContentView(view);
    }


    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        Toast.makeText(this, "渲染成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
        Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        Toast.makeText(this, "异常" + msg + errCode, Toast.LENGTH_SHORT).show();
        Log.e("weex", "onException: " + msg + errCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }

}
