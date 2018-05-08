package com.example.liuwei20180504.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.liuwei20180504.R;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ZhongXinActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.fanhui)
    TextView fanhui;
    @BindView(R.id.img)
    CircleImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.nicheng)
    TextView nicheng;
    @BindView(R.id.tuichu)
    Button tuichu;
    private View contentView;
    private PopupWindow popupWindow;
    private Button xiangce;
    private Button xiangce1;
    private Button zhao;
    private String path = Environment.getExternalStorageDirectory()+"/liuwei.png";
    private Bitmap bitmap;
    private Bitmap bitmap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_xin);
        ButterKnife.bind(this);


        //退出
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);
            }
        });
        //返回
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(bitmap);
                finish();
            }
        });

        contentView = View.inflate(ZhongXinActivity.this, R.layout.xiugai, null);

        //popu
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //设置
        //获得焦点
        popupWindow.setFocusable(true);
        //点击弹窗
        popupWindow.setTouchable(true);
        //点击空格外弹窗
        popupWindow.setOutsideTouchable(true);
        //背景图点击
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        final View paren = View.inflate(ZhongXinActivity.this,R.layout.activity_zhong_xin,null);


        //查找空间
        xiangce1 = contentView.findViewById(R.id.xiangce);
        zhao = contentView.findViewById(R.id.zhao);

        xiangce1.setOnClickListener(this);
        zhao.setOnClickListener(this);

        //点击任何地方都退出
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAtLocation(paren, Gravity.CENTER,0,0);
            }
        });

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.xiangce:
                xiangc();
                popupWindow.dismiss();
                break;
            case R.id.zhao:
                xiangji();
                popupWindow.dismiss();
                break;
                default:
                    break;
        }

    }

    private void xiangji() {
//获取软件自带的相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //保存图片
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));

        startActivityForResult(intent, 1001);
    }

    private void xiangc() {
        //调取相册
        Intent intent = new Intent(Intent.ACTION_PICK);

        //设置图片类型
        intent.setType("image/*");

        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001 && requestCode==RESULT_OK){

            Intent intent = new Intent("com.android.camera.action.CROP");

            //格式
            intent.setDataAndType(Uri.fromFile(new File(path)),"image/*");

            //将图片交给裁剪   格式
            intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");

            //是否裁剪
            intent.putExtra("CROP", true);

            //比例
            intent.putExtra("aspactX", 1);
            //比例
            intent.putExtra("aspactY", 1);

            //图片大小
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 250);

            //是否人脸识别
            intent.putExtra("onFaceDetection", false);

            //返回
            intent.putExtra("return-data", true);

            //返回
            startActivityForResult(intent, 2000);
        }

        if (requestCode == 1000 && resultCode == RESULT_OK) {

            //图片裁剪
            Uri uri = data.getData();

            //调取裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");

            //设置图片
            intent.setDataAndType(uri, "image/*");

            //比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);

            //大小
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 250);


            //返回
            intent.putExtra("return-data", true);

            startActivityForResult(intent, 2000);
        }
        if (requestCode == 2000 && resultCode == RESULT_OK) {
            bitmap = data.getParcelableExtra("data");
            img.setImageBitmap(bitmap);

        }
    }
}
