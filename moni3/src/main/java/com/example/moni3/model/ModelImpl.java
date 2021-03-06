package com.example.moni3.model;

import com.example.moni3.bean.NewsBean;
import com.example.moni3.util.ICallBack;
import com.example.moni3.util.OkHttpUtils;

import java.io.IOException;
import java.util.Map;

public class ModelImpl implements Imodel{
    @Override
    public void requestData(String url, final MyCallBack myCallBack) {
        OkHttpUtils.getInstance().doGet(url, NewsBean.class, new ICallBack() {
            @Override
            public void success(Object o) {
                myCallBack.getData(o);
            }

            @Override
            public void failed(IOException e) {

            }
        });
    }
}
