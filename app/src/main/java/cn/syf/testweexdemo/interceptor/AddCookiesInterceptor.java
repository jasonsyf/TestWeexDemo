package cn.syf.testweexdemo.interceptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Jason_Sunyf on 2017/11/14 0014.
 * Email： jason_sunyf@163.com
 * 读取缓存缓存的cookies信息,添加到header里
 */
public class AddCookiesInterceptor implements Interceptor {
    private Context context;
    private String lang;

    public AddCookiesInterceptor(Context context, String lang) {
        super();
        this.context = context;
        this.lang = lang;

    }

    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        Observable.just(sharedPreferences.getString("cookie", ""))
                .subscribe(s -> {
                    //添加cookie
                    builder.addHeader("cookie", s);
                    Log.d("http","=============AddCookiesInterceptorhttp============="+"\n"+s);
                });
        return chain.proceed(builder.build());
    }
}

