package com.example.switchnetwork;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class VollyHttp implements IHttp {

    public RequestQueue mRequestQueue = null;

    public VollyHttp(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(Map<String, Object> params, String url, final ICallBack callBack) {
        Log.e("VollyeHttp","=====get=====");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError();
            }
        });
        mRequestQueue.add(stringRequest);
    }

    @Override
    public void post(Map<String, Object> params, String url, final ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError();
            }
        });
        mRequestQueue.add(stringRequest);
    }
}
