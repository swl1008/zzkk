package com.example.moni3.util;

import java.io.IOException;

public interface ICallBack {
    void success(Object o);
    void failed(IOException e);
}
