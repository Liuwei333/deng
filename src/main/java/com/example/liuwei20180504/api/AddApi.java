package com.example.liuwei20180504.api;

import com.example.liuwei20180504.bean.XiangBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface AddApi {
    @GET("getProductDetail")
    Call<XiangBean>doAdd(@Query("pid")String pid,@Query("source")String source);
}
