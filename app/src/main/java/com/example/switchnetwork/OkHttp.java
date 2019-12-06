package com.example.switchnetwork;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp implements IHttp {

    private OkHttpClient mOkHttpClient;
    public Handler mHandler;

    public OkHttp(){
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    @Override
    public void get(Map<String, Object> params, String url, final ICallBack callBack) {
        Log.e("OkHttp","=====get=====");

        Request build = new Request.Builder().get().url(url).build();
        mOkHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean successful = response.isSuccessful();
                solveResponse(response,successful,callBack);
            }
        });
    }

    @Override
    public void post(Map<String, Object> params, String url, final ICallBack callBack) {

        final RequestBody requestBody = getRequestBody(params);
        Request build = new Request.Builder().post(requestBody).url(url).build();
        mOkHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean successful = response.isSuccessful();
                solveResponse(response,successful,callBack);
            }
        });

    }

    /**
     * 处理响应体的结果
     * @param response  响应体
     * @param successful  成功
     * @param iCallBack  成功失败回调
     */
    private void solveResponse(Response response, final boolean successful, final ICallBack iCallBack) throws IOException {
        final String string = response.body().string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (successful){
                    iCallBack.onSuccess(string);
                }else{
                    iCallBack.onError();
                }
            }
        });
    }

    /**
     * 得到一个请求体
     * @param params
     * @return
     */
    private RequestBody getRequestBody(Map<String,Object> params){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,Object> objectEntry:params.entrySet()){
            String key = objectEntry.getKey();
            builder.add(key,objectEntry.getValue().toString());
        }
        return builder.build();
    }
}
