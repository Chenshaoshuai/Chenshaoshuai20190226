package com.example.asus.chenshaoshuai20190226.model;

import com.example.asus.chenshaoshuai20190226.callback.MyCallBack;
import com.example.asus.chenshaoshuai20190226.notwork.RetrofitManger;
import com.google.gson.Gson;

public class IModelImpl implements IModel {
    @Override
    public void getRequest(String url, final Class clazz, final MyCallBack callBack) {
        RetrofitManger.getmRetrofitManger().get(url, new RetrofitManger.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data,clazz);
                callBack.onSuccess(o);

            }

            @Override
            public void onFails(String error) {
               callBack.onSuccess(error);
            }
        });
    }
}
