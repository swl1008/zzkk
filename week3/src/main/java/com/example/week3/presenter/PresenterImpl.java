package com.example.week3.presenter;

import com.example.week3.model.ModelImpl;
import com.example.week3.model.MyCallBack;
import com.example.week3.view.Iview;

import java.util.Map;

public class PresenterImpl implements Ipresenter {
    private Iview iView;
    private ModelImpl model;

    public PresenterImpl(Iview iview) {
        iView = iview;
        model = new ModelImpl();
    }

    @Override
    public void startRequest(String url, Map<String, String> params, Class clazz) {
        model.requestData(url, new MyCallBack() {
            @Override
            public void getData(Object o) {
                iView.getRequest(o);
            }
        });
    }

    public void onDetach(){
        if(model!=null){
            model = null;
        }
        if(iView!=null){
            iView = null;
        }
    }

}
