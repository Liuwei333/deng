package com.example.liuwei20180504.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.liuwei20180504.R;
import com.example.liuwei20180504.api.AddApi;
import com.example.liuwei20180504.bean.XiangBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class XiangActivity extends AppCompatActivity {

    @BindView(R.id.img)
    Banner banner;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.pid)
    TextView pid;
    @BindView(R.id.videoView)
    VideoView videoView;

    String uri1="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        ButterKnife.bind(this);


        //视频
        viedeo();

        //接受
        Intent intent = getIntent();

        //设置图片加载器
        banner.setImageLoader(new GlideImageLaoder());
        //实例化图片集合
        List<String> mListImage = new ArrayList<>();
        //将图片放入集合中
        mListImage.add("https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg!q70.jpg");
        mListImage.add("https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg!q70.jpg");
        mListImage.add("https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg!q70.jpg");

        //设置Banner图片集合
        banner.setImages(mListImage);
        //设置Banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        banner.setDelayTime(1000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.start();

        //名字
        String name1 = intent.getStringExtra("name");
        name.setText(name1);

        //金钱
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        String abc = bundle.getString("deptname");
        price.setText(abc);

//pid
        Bundle bundle1 = new Bundle();
        bundle1 = this.getIntent().getExtras();
        final String pid1 = bundle1.getString("pid");
        pid.setText(pid1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://www.zhaoapi.cn/product/")
                        .build();

                AddApi addApi = retrofit.create(AddApi.class);

                addApi.doAdd(pid1, "android").enqueue(new Callback<XiangBean>() {
                    @Override
                    public void onResponse(Call<XiangBean> call, Response<XiangBean> response) {

                        Toast.makeText(XiangActivity.this, "下单成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<XiangBean> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void viedeo() {

        Uri uri = Uri.parse(uri1);

        //控制器
        videoView.setMediaController(new MediaController(this));

        //会掉
    videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());

    //视频路径
        videoView.setVideoURI(uri);

        //播放
        videoView.start();
    }

    private class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {

            Toast.makeText(XiangActivity.this,"播放完成",Toast.LENGTH_SHORT).show();
        }
    }
}
