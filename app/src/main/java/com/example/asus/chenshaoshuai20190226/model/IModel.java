package com.example.asus.chenshaoshuai20190226.model;

import com.example.asus.chenshaoshuai20190226.callback.MyCallBack;

public interface IModel {
    void getRequest(String url, Class clazz, MyCallBack callBack);
}
