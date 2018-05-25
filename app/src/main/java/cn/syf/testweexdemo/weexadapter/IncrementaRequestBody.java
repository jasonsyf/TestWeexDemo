package cn.syf.testweexdemo.weexadapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @author : Jason_Sunyf
 * @date : 2018年05月25日10时19分
 * E-mail  : jason_sunyf@163.com
 */
public class IncrementaRequestBody extends RequestBody {

    private RequestBody realBody;
    private WeexOkHttpAdapter.RequestListener requestListener;

    private BufferedSink bufferedSink;

    public IncrementaRequestBody(RequestBody realBody, WeexOkHttpAdapter.RequestListener requestListener){
        this.realBody = realBody;
        this.requestListener = requestListener;
    }
    @Override
    public MediaType contentType() {
        return realBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return realBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            //包装
            bufferedSink = Okio.buffer(sink(sink));
        }
        //写入
        realBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            long totalConsumed = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);

                totalConsumed += byteCount;

                requestListener.onRequest(totalConsumed, contentLength(), totalConsumed == contentLength());
            }
        };
    }
}
