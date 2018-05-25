package cn.syf.testweexdemo.weexmodule;

import android.widget.Toast;

import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

/**
 * @author : Jason_Sunyf
 * @date : 2018年05月23日16时55分
 * E-mail  : jason_sunyf@163.com
 */
public class ToastModule extends WXModule {

    @WXModuleAnno(runOnUIThread = true)
    public void showToast(){
        Toast.makeText(mWXSDKInstance.getContext(),"this is js call native toast",Toast.LENGTH_LONG).show();
    }

    @WXModuleAnno(runOnUIThread = true)
    public void showToastWithMsg(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(),msg,Toast.LENGTH_LONG).show();
    }
}


