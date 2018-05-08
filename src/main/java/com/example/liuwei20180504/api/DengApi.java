package com.example.liuwei20180504.api;

import com.example.liuwei20180504.bean.DengBean;
import com.example.liuwei20180504.bean.ZhuCeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface DengApi {

    //登录
    @GET("login")
    Observable<DengBean>doDeng(@Query("mobile")String mobile,@Query("password")String password);

    //注册
    @GET("reg")
    Observable<ZhuCeBean>doZhu(@Query("mobile")String mobile,@Query("password")String password);
}
