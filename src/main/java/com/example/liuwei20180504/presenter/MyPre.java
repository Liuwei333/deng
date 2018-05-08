package com.example.liuwei20180504.presenter;

import com.example.liuwei20180504.bean.ShopBean;
import com.example.liuwei20180504.model.MyModel;
import com.example.liuwei20180504.model.MyModelView;
import com.example.liuwei20180504.view.LogView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class MyPre implements MyPreView {
    LogView logView;
    private final MyModelView myModelView;

    public MyPre(LogView logView){
this.logView=logView;
        myModelView = new MyModel();
    }
    @Override
    public void toPreView(List<ShopBean.DataBean> data) {
logView.toBackView(data);

    }

    @Override
    public void setNet(String detUri) {
        myModelView.toModelView(detUri,this);
    }

    @Override
    public void onDestroy() {
        logView=null;
        System.gc();
    }
}
