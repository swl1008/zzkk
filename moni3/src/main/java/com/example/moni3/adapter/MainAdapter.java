package com.example.moni3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moni3.R;
import com.example.moni3.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsBean.DataBean> data;
    private Context context;
    private static final int TYPE_IMAGE_ONE = 0;
    private static final int TYPE_IMAGE_TWO= 1;
    private static final int TYPE_IMAGE_THREE= 2;
    public MainAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }
    public void setData(List<NewsBean.DataBean> datas){
        data.clear();
        if(datas!=null){
            data.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void addData(List<NewsBean.DataBean> datas){
        if(datas!=null){
            data.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder rh = null;
        if(i == TYPE_IMAGE_ONE){
            View view = LayoutInflater.from(context).inflate(R.layout.image_one,viewGroup,false);
            rh = new TheOne(view);
        }else if(i == TYPE_IMAGE_TWO){
            View view = LayoutInflater.from(context).inflate(R.layout.image_two,viewGroup,false);
            rh = new TheTwo(view);
        }else if(i == TYPE_IMAGE_THREE){
            View view = LayoutInflater.from(context).inflate(R.layout.image_three,viewGroup,false);
            rh = new TheThree(view);
        }
        return rh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        switch (type){
            case TYPE_IMAGE_ONE:
                TheOne theOne = (TheOne) viewHolder;
                theOne.title.setText(data.get(i).getTitle());
                Glide.with(context).load(data.get(i).getThumbnail_pic_s()).into(theOne.image_one);
                break;
            case TYPE_IMAGE_TWO:
                TheTwo theTwo = (TheTwo) viewHolder;
                theTwo.title.setText(data.get(i).getTitle());
                Glide.with(context).load(data.get(i).getThumbnail_pic_s()).into(theTwo.image_one);
                Glide.with(context).load(data.get(i).getThumbnail_pic_s02()).into(theTwo.image_two);
                break;
            case TYPE_IMAGE_THREE:
                TheThree theThree = (TheThree) viewHolder;
                theThree.title.setText(data.get(i).getTitle());
                Glide.with(context).load(data.get(i).getThumbnail_pic_s()).into(theThree.image_one);
                Glide.with(context).load(data.get(i).getThumbnail_pic_s02()).into(theThree.image_two);
                Glide.with(context).load(data.get(i).getThumbnail_pic_s03()).into(theThree.image_three);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        String p2 = data.get(position).getThumbnail_pic_s02();
        String p3 = data.get(position).getThumbnail_pic_s03();
        if (p2==null && p3==null){
            return TYPE_IMAGE_ONE;
        }else if (p2==null || p3==null){
            return TYPE_IMAGE_TWO;
        }else{
            return TYPE_IMAGE_THREE;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class TheOne extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView image_one;
        private ConstraintLayout constraintLayout;
        public TheOne(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image_one = itemView.findViewById(R.id.image_one);
            constraintLayout = itemView.findViewById(R.id.con);
        }
    }
    public class TheTwo extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView image_one;
        private ImageView image_two;
        private ConstraintLayout constraintLayout;
        public TheTwo(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image_one = itemView.findViewById(R.id.image_one);
            image_two = itemView.findViewById(R.id.image_two);
            constraintLayout = itemView.findViewById(R.id.con);
        }
    }
    public class TheThree extends RecyclerView.ViewHolder{
        private final TextView title;
        private final ImageView image_one;
        private final ImageView image_two;
        private final ImageView image_three;
        private final ConstraintLayout constraintLayout;
        public TheThree(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image_one = itemView.findViewById(R.id.image_one);
            image_two = itemView.findViewById(R.id.image_two);
            image_three = itemView.findViewById(R.id.image_three);
            constraintLayout = itemView.findViewById(R.id.con);
        }
    }
}


