package com.example.asus.chenshaoshuai20190226.notwork;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface BaseApis<T> {

    @GET
    Observable<ResponseBody> get(@Url String url);
}
