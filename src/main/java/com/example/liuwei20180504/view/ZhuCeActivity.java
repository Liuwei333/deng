package com.example.liuwei20180504.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwei20180504.MainActivity;
import com.example.liuwei20180504.R;
import com.example.liuwei20180504.bean.ZhuCeBean;
import com.example.liuwei20180504.utils.DengZhuUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZhuCeActivity extends AppCompatActivity {


    @BindView(R.id.fanhui)
    TextView fanhui;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.pass1)
    EditText pass1;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.zhuce)
    Button zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        ButterKnife.bind(this);

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retion();
            }
        });

        //fanhui
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("");
                finish();
            }
        });
    }

    private void retion() {

        //赋值
        String name1 = name.getText().toString();
        String shupass = pass.getText().toString();
        String quepass = pass1.getText().toString();
        String email1 = email.getText().toString();

        if (shupass.equals(quepass)) {
            Observable<ZhuCeBean> zhuCeBeanObservable = DengZhuUtils.getInstance().getApi().doZhu(name1, shupass);
            zhuCeBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ZhuCeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ZhuCeBean value) {

                    String msg = value.getMsg();
                    if (msg.equals("注册成功")) {
                        Intent intent = new Intent(ZhuCeActivity.this, MainActivity.class);
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

        }else{
            Toast.makeText(ZhuCeActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
        }


    }
}
