package com.example.moni3.model;

import com.example.moni3.util.ICallBack;
import com.example.moni3.util.OkHttpUtils;

import java.io.IOException;
import java.util.Map;

public class ModelImpl implements Imodel{
    private MyCallBack myCallBack;
    @Override
    public void requestData(String url, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getInstance().yibuPost(url, params, clazz, new ICallBack() {
            @Override
            public void success(Object o) {
                myCallBack.getData(o);
            }

            @Override
            public void failed(IOException e) {
                myCallBack.getData(e.getMessage());
            }
        });
    }
}
