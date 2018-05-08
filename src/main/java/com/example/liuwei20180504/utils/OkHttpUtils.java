package com.example.liuwei20180504.utils;

import android.os.Environment;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/5/4.
 */

public class OkHttpUtils {

    static  OkHttpClient client;
    private OkHttpUtils(){};

    public static OkHttpClient getInstance(){


        if (client==null) {
            //更加安全
            synchronized (OkHttpUtils.class) {
                //缓存的地方     mnt/sdcard
                File file = new File(Environment.getExternalStorageDirectory(), "cache11");
                client = new OkHttpClient().newBuilder()
                        .readTimeout(3000, TimeUnit.SECONDS)   //设置读取超时时间
                        .connectTimeout(3000, TimeUnit.SECONDS) //设置连接的超时时间

                        .cache(new Cache(file, 10 * 1024))
                        .build();
            }
        }
        return client;
    }
//用来上传图片的

    //url  , 图片  ，参数    Callback
    public static  void upImage(String url, File file, String filenName, Map<String,String> params, Callback callback){

        OkHttpClient client = getInstance();


        //requestBody的实现类  Formbody
        MultipartBody.Builder builder = new MultipartBody.Builder();

        if (params!=null){
            for (String key :params.keySet()){
                builder.addFormDataPart(key,params.get(key));
            }
        }

        //设置类型
        builder.setType(MultipartBody.FORM);


        builder.addFormDataPart("file",filenName, RequestBody.create(MediaType.parse("application/octet-stream"),file));

        // builder.setType(MultipartBody.FORM);
        // builder.addFormDataPart("file",filenName,RequestBody.create(MediaType.parse("application/octet-stream"),file));
        //builder.addFormDataPart("file","aa.png",builder.build());

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        client.newCall(request).enqueue(callback);


    }

}
