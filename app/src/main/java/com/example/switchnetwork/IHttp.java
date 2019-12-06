package com.example.switchnetwork;

import java.util.Map;

public interface IHttp {

    void get(Map<String,Object> params,String url,ICallBack callBack);

    void post(Map<String,Object> params,String url,ICallBack callBack);
}
