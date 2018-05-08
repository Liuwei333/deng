package com.example.liuwei20180504.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuwei20180504.R;
import com.example.liuwei20180504.bean.ShopBean;
import com.example.liuwei20180504.view.GeRenActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    List<ShopBean.DataBean> data;
    public MyAdapter(Context context, List<ShopBean.DataBean> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        //加入布局
        View inflate = View.inflate(context, R.layout.buju, null);

        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final MyHolder holder1 = (MyHolder) holder;

        //生成图片
        String[] split = data.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder1.sim.setImageURI(uri);

        //文字
        holder1.name.setText(data.get(position).getTitle());

        //点击事件
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行回掉
                int layoutPosition = holder1.getLayoutPosition();
                listener.onClick(holder1.itemView,layoutPosition);
            }
        });
    }
    OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public SimpleDraweeView sim;
        public TextView name;

        public MyHolder(View view) {
            super(view);
            sim = view.findViewById(R.id.sim);
            name = view.findViewById(R.id.name);
        }
    }

    //接口  用于点击事件
    public interface  OnItemClickListener{
        void onClick(View v,int position);
    }


}
