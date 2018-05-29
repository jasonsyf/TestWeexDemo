package cn.syf.testweexdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.taobao.weex.WXSDKInstance;

import java.util.HashMap;
import java.util.Map;

public class TestSendParamsActivity extends AppCompatActivity {
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_send_params);
        mButton = findViewById(R.id.btn1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXSDKInstance wxsdkInstance = new WXSDKInstance(TestSendParamsActivity.this);
                Map<String,Object> params=new HashMap<>();
                params.put("account","1");
                params.put("pwd","admin");
                wxsdkInstance.fireGlobalEventCallback("params",params);
                Intent intent = new Intent();
                intent.setClass(TestSendParamsActivity.this, MainActivity.class);
                intent.putExtra("account", "admin");
                intent.putExtra("pwd", "admin");
                startActivity(intent);
            }
        });

    }
}
