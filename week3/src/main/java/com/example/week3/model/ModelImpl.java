package com.example.week3.model;

import com.example.week3.bean.GoodsBean;
import com.example.week3.utils.ICallBack;
import com.example.week3.utils.OkHttpUtil;

import java.io.IOException;

public class ModelImpl implements Imodel {
    @Override
    public void requestData(String url, final MyCallBack myCallBack) {
        OkHttpUtil.getInstance().doGet(url, GoodsBean.class, new ICallBack() {
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
