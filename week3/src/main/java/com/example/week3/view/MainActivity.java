package com.example.week3.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week3.R;
import com.example.week3.adapter.GoodsAdapter;
import com.example.week3.bean.GoodsBean;
import com.example.week3.presenter.PresenterImpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity implements Iview{

    private PresenterImpl presenter;
    private ImageView image_change;
    private TextView text_zonghe,text_price,text_xiao;
    private XRecyclerView xRecyclerView;
    private GoodsAdapter adapter;
    private int page;
    private int sort=0;
    private boolean p=true;
    private String urlstr="http://www.zhaoapi.cn/product/searchProducts?keywords=手机&page="+page+"&sort="+sort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresenterImpl( this);
        initView();
    }
    private void initView(){
        xRecyclerView = findViewById(R.id.recycle);
        image_change = findViewById(R.id.image_change);
        text_zonghe = findViewById(R.id.text_zonghe);
        text_price = findViewById(R.id.text_price);
        text_xiao = findViewById(R.id.text_xiao);
        adapter = new GoodsAdapter(this);
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();

            }

            @Override
            public void onLoadMore() {
                initData();

            }
        });
        initData();
        image_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p==true){
                    xRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    p=false;
                }else {
                    xRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                    p=true;
                }
            }
        });
        text_zonghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort=0;
                initData();
            }
        });
        text_xiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort=1;
                initData();
            }
        });
        text_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort=2;
                initData();
            }
        });
        adapter.setOnClickListener(new GoodsAdapter.onClickCallBack() {
            @Override
            public void setOnClick(String imageView, String product, String price) {
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("image",imageView);
                intent.putExtra("product",product);
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getRequest(Object o) {
        if(o instanceof GoodsBean){
            GoodsBean bean = (GoodsBean) o;
            if (bean == null){
                Toast.makeText(MainActivity.this,bean.getMsg(), Toast.LENGTH_SHORT).show();
            }else{
                if(page == 1){
                    adapter.setData(bean.getData());
                }else{
                    adapter.addData(bean.getData());
                }

                xRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

                page++;
                xRecyclerView.refreshComplete();
                xRecyclerView.loadMoreComplete();
            }

        }
    }
    private void initData() {
        presenter.startRequest(urlstr+page,null,GoodsBean.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
