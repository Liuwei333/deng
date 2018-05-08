package com.example.liuwei20180504;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liuwei20180504.bean.DengBean;
import com.example.liuwei20180504.utils.DengZhuUtils;
import com.example.liuwei20180504.view.GeRenActivity;
import com.example.liuwei20180504.view.ZhuCeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.denglu)
    Button denglu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        EventBus.getDefault().register(this);
        //跳转
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this, ZhuCeActivity.class);
             startActivity(intent);
            }
        });

        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取值
                retion();
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)

    public void ret(String json){
      name.setText(json);
    }

    //解除注册


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void retion() {

        //获取值
        String name1 = name.getText().toString();
        String pass1 = pass.getText().toString();

        //登录
        Observable<DengBean> dengBeanObservable = DengZhuUtils.getInstance().getApi().doDeng(name1, pass1);
        dengBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DengBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DengBean value) {

                String msg = value.getMsg();
                if(msg.equals("登录成功")){
                    Intent intent = new Intent(MainActivity.this, GeRenActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
