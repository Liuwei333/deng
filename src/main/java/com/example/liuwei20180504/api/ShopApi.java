package com.example.liuwei20180504.api;

import com.example.liuwei20180504.bean.ShopBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface ShopApi {

    @GET("getProducts")
    Call<ShopBean>doShop(@Query("pscid")String pascid);
}
