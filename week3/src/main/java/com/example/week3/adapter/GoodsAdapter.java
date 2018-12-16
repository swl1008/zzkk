package com.example.week3.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3.R;
import com.example.week3.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private List<GoodsBean.DataBean> data;
    private Context context;
    public GoodsAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }
    public void setData(List<GoodsBean.DataBean> datas){
        data.clear();
        if(datas!=null){
            data.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void addData(List<GoodsBean.DataBean> datas){
        if(datas!=null){
            data.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder vh = null;
        View view = LayoutInflater.from(context).inflate(R.layout.goods_item,viewGroup,false);
        vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.product.setText(data.get(i).getTitle());
        viewHolder.price.setText( data.get(i).getPrice()+"");
        List<String> images = new ArrayList<>();
        String images2 = data.get(i).getImages();
        Pattern pattern = Pattern.compile("\\|");
        final String[] img = pattern.split(images2);
        Glide.with(context).load(img[0]).into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              clickCallBack.setOnClick(im c,data.get(i).getTitle(),data.get(i).getPrice());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView product;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            product = itemView.findViewById(R.id.item_product);
            price = itemView.findViewById(R.id.item_price);
        }
    }
    private onClickCallBack clickCallBack;
    public interface onClickCallBack{
        void setOnClick(String imageView, String product, String price);
    }
    public void setOnClickListener(onClickCallBack clickCallBack){
        this.clickCallBack = clickCallBack;
    }
}
