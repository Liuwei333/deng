package com.example.liuwei20180504.view;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/5/4.
 */

public class GlideImageLaoder extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }
    public ImageView createImageView(Context context) {

//使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的
// ImageView
SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);

        return simpleDraweeView;

    }
}
