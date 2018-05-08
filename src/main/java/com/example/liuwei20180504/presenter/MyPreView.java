package com.example.liuwei20180504.presenter;

import com.example.liuwei20180504.bean.ShopBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface MyPreView {
    void toPreView(List<ShopBean.DataBean> data);

    void setNet(String detUri);

    void onDestroy();
}
