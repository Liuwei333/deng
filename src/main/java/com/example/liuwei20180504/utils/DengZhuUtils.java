package com.example.liuwei20180504.utils;

import com.example.liuwei20180504.api.DengApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/4.
 */

public class DengZhuUtils {

    //单例

    private static DengZhuUtils instance;
    private final Retrofit retrofit;

    private DengZhuUtils(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addNetworkInterceptor(httpLoggingInterceptor);//网络缓存

        retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/user/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    public static  DengZhuUtils getInstance(){
        if(instance==null){
            synchronized (DengZhuUtils.class){
                if(null==instance){
                    instance = new DengZhuUtils();
                }
            }
        }
        return instance;
    }

    public DengApi getApi(){
        return retrofit.create(DengApi.class);
    }
}
