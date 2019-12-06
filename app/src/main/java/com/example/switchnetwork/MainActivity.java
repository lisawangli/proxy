package com.example.switchnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://apis.juhe.cn/mobile/get?phone=18856907654&key=5778e9d9cf089fc3b093b162036fc0e1";

        HttpProxy.getInstance().get(new HashMap<String, Object>(), url, new ICallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("MainAcitivity","result="+result);
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * get 请求
     */
    private void getRequest() {

        String url = "http://apis.juhe.cn/mobile/get?phone=18856907654&key=5778e9d9cf089fc3b093b162036fc0e1";


        /**
         * 1. int 类型 用于指定请求的方式（如GET或者POST）
         * 2. String类型 用于指定网络请求要连接的网址
         * 3. Listener类型 ,接收网络响应的接口，即只要得到本次请求对应的返回结果
         * 就会运行此接口中的onResponse方法
         * 4： ErrorListener类型， 用于接收当网络请求的过程中一旦发生了什么错误，
         * 就会调用本接口中的onErrorResponse方法
         * */
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("LUO", "========" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LUO", "========" + error.getMessage());
            }
        });

        //三，给请求对象设置tag标识
        stringRequest.setTag("get");
        //四，将请求添加到请求队列中，执行网络请求
        MyApplication.queue.add(stringRequest);
        //不要调用，看下方注意
        //MyApplication.getHttpQueues().start();
    }
}
