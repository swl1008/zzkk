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
    public void delDate(int i) {
        data.remove(i);
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        switch (type){
            case TYPE_IMAGE_ONE:
                final TheOne theOne = (TheOne) viewHolder;
                theOne.title.setText(data.get(i).getTitle());
                Glide.with(context).load(data.get(i).getThumbnail_pic_s()).into(theOne.image_one);

                theOne.image_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack !=null){
                            clickCallBack.setItemOnClick(theOne.image_one,i);
                        }
                    }
                });

                break;
            case TYPE_IMAGE_TWO:
                final TheTwo theTwo = (TheTwo) viewHolder;
                theTwo.title.setText(data.get(i).getTitle());
                Glide.with(context).load(data.get(i).getThumbnail_pic_s()).into(theTwo.image_one);
                Glide.with(context).load(data.get(i).getThumbnail_pic_s02()).into(theTwo.image_two);
                theTwo.image_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack !=null){
                            clickCallBack.setItemOnClick(theTwo.image_one,i);
                        }
                    }
                });
                theTwo.image_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack !=null){
                            clickCallBack.setItemOnClick(theTwo.image_two,i);
                        }
                    }
                });
                break;
            case TYPE_IMAGE_THREE:
                final TheThree theThree = (TheThree) viewHolder;
                theThree.title.setText(data.get(i).getTitle());
                Glide.with(context).load(data.get(i).getThumbnail_pic_s()).into(theThree.image_one);
                Glide.with(context).load(data.get(i).getThumbnail_pic_s02()).into(theThree.image_two);
                Glide.with(context).load(data.get(i).getThumbnail_pic_s03()).into(theThree.image_three);
                theThree.image_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack !=null){
                            clickCallBack.setItemOnClick(theThree.image_one,i);
                        }
                    }
                });
                theThree.image_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack !=null){
                            clickCallBack.setItemOnClick(theThree.image_two,i);
                        }
                    }
                });
                theThree.image_three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clickCallBack !=null){
                            clickCallBack.setItemOnClick(theThree.image_three,i);
                        }
                    }
                });
                break;
        }
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = viewHolder.getLayoutPosition();
                longClickCallBack.setLongItemOnClick(position-1);
                return false;
            }
        });
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
    private onClickCallBack clickCallBack;
    public interface onClickCallBack{
        void setItemOnClick(View item,int i);
    }
    public void setItemOnClickListener(onClickCallBack clickCallBack){
        this.clickCallBack = clickCallBack;
    }

    private onLongClickCallBack longClickCallBack;
    public interface onLongClickCallBack{
        void setLongItemOnClick(int i);
    }
    public void setLongItemOnClickListener(onLongClickCallBack longClickCallBack){
        this.longClickCallBack = longClickCallBack;
    }

}


