package cn.syf.testweexdemo.weexcomponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.syf.testweexdemo.R;

/**
 * @author : Jason_Sunyf
 * @date : 2018年06月02日16时02分
 * E-mail  : jason_sunyf@163.com
 */
public class WeexSpinner extends WXComponent<Spinner> {
    private Spinner spinner;
    private List<String> list;
    @Override
    protected Spinner initComponentHostView(@NonNull Context context) {
        spinner = new Spinner(context);
        return spinner;
    }


    public WeexSpinner(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
        super(instance, dom, parent, isLazy);
    }

    @WXComponentProp(name = "type")
    public <T> void initSpinner(int type){
        //这里的type实际项目当中查询数据库时必要的参数，所以预留出来
        list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add("霍比特人"+i);
        }
        //适配器
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),  R.layout.raw_spn,
                list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Map<String, Object> resp =new HashMap<>();
                resp.put("data", list.get(position));
                fireEvent("refresh",resp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @WXComponentProp(name = "value")
    public void onSpinnerValue(int value){
        spinner.setSelection(value);//这里直接传入的是下标
    }

    /**
     * 根据值, 设置spinner默认选中:
     * @param value   设置回显得值
     */
    @WXComponentProp(name = "value")
    public void onSpinnerValue(String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                break;
            }
        }
    }
}
