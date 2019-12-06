package com.example.switchnetwork;

import java.util.HashMap;
import java.util.Map;

public class HttpProxy implements IHttp {

    public static IHttp iHttp;
    private static HttpProxy instance;
    private Map<String,Object> mParams;

    public HttpProxy(){
        mParams = new HashMap<>();
    }

    public static HttpProxy getInstance(){
        if (instance==null){
            synchronized (HttpProxy.class){
                if (instance==null)
                    instance = new HttpProxy();
            }
        }
        return instance;
    }

    public static void init(IHttp http){
        iHttp = http;
    }

    @Override
    public void get(Map<String, Object> params,String url, ICallBack callBack) {
        iHttp.get(params,url,callBack);
    }

    @Override
    public void post(Map<String, Object> params, String url, ICallBack callBack) {
        iHttp.post(params,url,callBack);
    }
}
