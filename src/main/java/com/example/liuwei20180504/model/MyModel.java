package com.example.liuwei20180504.model;

import com.example.liuwei20180504.api.ShopApi;
import com.example.liuwei20180504.bean.ShopBean;
import com.example.liuwei20180504.presenter.MyPreView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/4.
 */

public class MyModel implements MyModelView {

    @Override
    public void toModelView(String uri, final MyPreView preView) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShopApi shopApi = retrofit.create(ShopApi.class);

        //一异步解析
        shopApi.doShop("1").enqueue(new Callback<ShopBean>() {
            @Override
            public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {

                //获取
                List<ShopBean.DataBean> data = response.body().getData();

                //发送到p成
                    preView.toPreView(data);


            }

            @Override
            public void onFailure(Call<ShopBean> call, Throwable t) {

            }
        });
    }
}
