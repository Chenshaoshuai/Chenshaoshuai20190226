package com.example.asus.chenshaoshuai20190226.notwork;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManger<T> {
    private static final String BASE_URL="http://172.17.8.100/movieApi/movie/";
    private OkHttpClient client;
    private static RetrofitManger mRetrofitManger;

    public static RetrofitManger getmRetrofitManger() {
        if(mRetrofitManger == null){
          synchronized (RetrofitManger.class){
              if(null==mRetrofitManger){
                  mRetrofitManger = new RetrofitManger();
              }
          }
        }
        return mRetrofitManger;
    }
    private BaseApis mBaseApis;

    private RetrofitManger(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
//                        userId ：11249
//                        sessionId ：155056366467311249


                        Request.Builder builder = request.newBuilder();
                        builder.method(request.method(),request.body());
                        builder.addHeader("userId","11249");
                        builder.addHeader("sessionId","155056366467311249");
                        Request build = builder.build();
                        return chain.proceed(build);
                    }
                })
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();

        mBaseApis = retrofit.create(BaseApis.class);
    }
    //get
    public RetrofitManger get(String url,HttpListener listener){
        mBaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));


        return mRetrofitManger;
    }
    public Observer getObserver(final HttpListener listener){
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(listener!=null){
                    listener.onFails(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    if(listener!=null){
                        listener.onSuccess(string);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(listener!=null){
                        listener.onFails(e.getMessage());
                    }
                }
            }


        };
        return observer;
    }

     private HttpListener  listener;

    public void  result(HttpListener listener){
        this.listener = listener;
    }

    public interface HttpListener {
        void onSuccess(String data);
        void onFails(String error);
    }
}
