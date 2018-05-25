package cn.syf.testweexdemo.weexadapter;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/**
 * @author : Jason_Sunyf
 * @date : 2018年05月25日10时20分
 * E-mail  : jason_sunyf@163.com
 */
public class IncrementalResponseBody extends ResponseBody {

    private ResponseBody realBody;
    private WeexOkHttpAdapter.ResponseListener responseListener;

    public IncrementalResponseBody(ResponseBody realBody, WeexOkHttpAdapter.ResponseListener responseListener) {
        this.realBody = realBody;
        this.responseListener = responseListener;
    }

    @Override
    public MediaType contentType() {
        return realBody.contentType();
    }

    @Override
    public long contentLength() {
        return realBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(source(realBody.source()));
    }

    private Source source(Source source) {

        return new ForwardingSource(source) {
            long totalConsumed = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long currentConsumed = super.read(sink, byteCount);
                totalConsumed += currentConsumed != -1 ? currentConsumed : 0;
                responseListener.onResponse(totalConsumed, IncrementalResponseBody.this.contentLength(), currentConsumed == -1);
                return currentConsumed;
            }
        };
    }
}