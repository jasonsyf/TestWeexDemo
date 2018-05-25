package cn.syf.testweexdemo.interceptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;


import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Jason_Sunyf on 2017/11/14 0014.
 * Email： jason_sunyf@163.com
 * 接受请求时服务器传回的cookie信息,并将其缓存到shareProference
 */

public class ReceivedCookiesInterceptor implements Interceptor {
    SharedPreferences sharedPreferences;
    public ReceivedCookiesInterceptor(Context context) {
        super();
        sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
    }

    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        Log.d("http", "originalResponse" + originalResponse.toString());
        if (!originalResponse.headers("set-cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.fromIterable(originalResponse.headers("set-cookie"))
                    .map(s -> {
                        String[] cookieArray = s.split(";");
                        return cookieArray[0];
                    })
                    .subscribe(cookie -> cookieBuffer.append(cookie).append(";"));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cookie", cookieBuffer.toString());
            Log.d("http","http"+"=============ReceivedCookiesInterceptor============="+"\n" + cookieBuffer.toString());
            editor.apply();
        }

        return originalResponse;
    }
}