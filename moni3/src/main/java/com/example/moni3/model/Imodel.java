package com.example.moni3.model;

import java.util.Map;

public interface Imodel {
    void requestData(String url, Map<String,String> params, Class clazz, MyCallBack myCallBack);
}
