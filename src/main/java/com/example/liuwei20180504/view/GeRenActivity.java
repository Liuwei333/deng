package com.example.liuwei20180504.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwei20180504.R;
import com.example.liuwei20180504.adapter.MyAdapter;
import com.example.liuwei20180504.bean.ShopBean;
import com.example.liuwei20180504.presenter.MyPre;
import com.example.liuwei20180504.presenter.MyPreView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeRenActivity extends AppCompatActivity implements LogView{

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rcy)
    RecyclerView rcy;

    String detUri="https://www.zhaoapi.cn/product/";
    private MyPreView myPreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren);
        ButterKnife.bind(this);

        //注册
        EventBus.getDefault().register(this);
        //点击事件
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeRenActivity.this,ZhongXinActivity.class);
                startActivity(intent);
            }
        });


        //生成p层 传回网址
        myPreView = new MyPre(this);
        myPreView.setNet(detUri);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
            public void reti(Bitmap bitmap){
         img.setImageBitmap(bitmap);
    }

    //接触注册


    @Override
    public void toBackView(final List<ShopBean.DataBean> data) {

        //解析数据
        if(data!=null){

            //生成格式
            rcy.setLayoutManager(new LinearLayoutManager(GeRenActivity.this,LinearLayoutManager.VERTICAL,false));
            //适配器
            MyAdapter myAdapter = new MyAdapter(GeRenActivity.this,data);
            rcy.setAdapter(myAdapter);


            //进行适配器会掉
            myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void onClick(View v, int position) {
                    //生成图片
                    String[] split = data.get(position).getImages().split("\\|");
                    Uri uri = Uri.parse(split[0]);

                    Intent intent = new Intent(GeRenActivity.this,XiangActivity.class);
                    intent.putExtra("photo",data.get(position).getImages());


                    intent.putExtra("name",data.get(position).getTitle());

                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("pid", data.get(position).getPid());
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(GeRenActivity.this,"是空的",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myPreView!=null){
            myPreView.onDestroy();
            myPreView=null;
            System.gc();
        }
        EventBus.getDefault().unregister(this);
    }
}
