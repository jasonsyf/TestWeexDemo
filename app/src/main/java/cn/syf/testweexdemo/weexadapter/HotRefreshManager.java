/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.syf.testweexdemo.weexadapter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;


import java.io.IOException;

import cn.syf.testweexdemo.constants.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;

/**
 */
public class HotRefreshManager {

    private static final String TAG = "HotRefreshManager";

    private static HotRefreshManager hotRefreshInstance = new HotRefreshManager();
    private WebSocket mWebSocket = null;
    private Handler mHandler = null;

    private HotRefreshManager() {
    }

    public static HotRefreshManager getInstance() {
        return hotRefreshInstance;
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    public boolean disConnect() {
        if (mWebSocket != null) {
            try {
                mWebSocket.close(1000, "activity finish!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean connect(String url) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).addHeader("sec-websocket-protocol", "echo-protocol").build();
        WebSocketCall.create(httpClient, request).enqueue(new WXWebSocketListener(url));

        return true;
    }

    class WXWebSocketListener implements WebSocketListener {

        private String mUrl;

        WXWebSocketListener(String url) {
            mUrl = url;
        }


        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            mWebSocket = webSocket;
        }

        @Override
        public void onFailure(IOException e, Response response) {
            mWebSocket = null;
        }

        @Override
        public void onMessage(ResponseBody message) throws IOException {

            if (TextUtils.equals("refresh", message.string()) && mHandler != null) {
                mHandler.obtainMessage(Constants.HOT_REFRESH_REFRESH, 0, 0, mUrl).sendToTarget();
            }
        }

        @Override
        public void onPong(Buffer payload) {

        }

        @Override
        public void onClose(int code, String reason) {
            mWebSocket = null;
        }

    }
}
