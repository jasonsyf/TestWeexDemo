package cn.syf.testweexdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : Jason_Sunyf
 * @date : 2018年05月28日14时11分
 * E-mail  : jason_sunyf@163.com
 */
public class MyWXSDKInstance extends WXSDKInstance {
    SharedPreferences mPreferences = getContext().getSharedPreferences("jsbundle", Context.MODE_PRIVATE);

    public MyWXSDKInstance(Context context) {
        super(context);
    }

    @Override
    public void render(String pageName, String template, Map<String, Object> options, String jsonInitData, WXRenderStrategy flag) {
        saveWeexFile(template);
        super.render(pageName, template, options, jsonInitData, flag);
    }

    @Override
    public void renderByUrl(String pageName, String url, Map<String, Object> options, String jsonInitData, WXRenderStrategy flag) {
        String local = "";
        if (TextUtils.isEmpty(url)
                || Md5Utils.md5(url).equals(mPreferences.getString("jsUrl", ""))
                ) {
            local = getLocalJs();//获取本地JS路径
        }

        if (!TextUtils.isEmpty(local)) {
            super.render(pageName, local, options, jsonInitData, flag);
        } else {
            super.renderByUrl(pageName, url, options, jsonInitData, flag);
            SharedPreferences preferences = getContext().getSharedPreferences("jsbundle", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("jsUrl", Md5Utils.md5(url));
            editor.apply();
        }
    }

    /**
     * 获取本地JS路径
     */
    private String getLocalJs() {
        try {
            File f = new File(getContext().getFilesDir(), "local_js.txt");
            if (f.exists()) {
                return "file://" + f.getAbsolutePath();
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 异步存储JS Bundle RX实现
     *
     * @param template
     */
    private void saveWeexFile(String template) {
        if (isLocalFile(getBundleUrl())) {
            return;
        }
        Observable
                .just(template)
                .map(new Function<String, Boolean>() {
                    @Override
                    public Boolean apply(String s) throws Exception {
                        //weex对文件名不敏感，存txt文件
                        return saveFile(getContext().getFilesDir().getAbsolutePath(), "local_js.txt", s.getBytes("UTF-8"));
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //缓存成功
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private boolean saveFile(String path, String name,  byte[] bytes) {
        File file = new File(path, name);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是本地文件还是网络url
     */
    private boolean isLocalFile(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Uri uri = Uri.parse(url);
        if (uri != null && TextUtils.equals(uri.getScheme(), "file")) {
            return true;
        }
        return false;
    }
}
