package com.example.week3.utils;

import java.io.IOException;

public interface ICallBack {
    void success(Object o);
    void failed(IOException e);
}
