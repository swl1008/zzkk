package com.example.moni3.presenter;

import java.util.Map;

public interface Ipresenter {
    void startRequest(String url, Map<String,String> params, Class clazz);
}
