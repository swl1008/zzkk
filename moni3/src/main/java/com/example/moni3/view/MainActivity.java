package com.example.moni3.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moni3.R;
import com.example.moni3.adapter.MainAdapter;
import com.example.moni3.api.Apis;
import com.example.moni3.bean.NewsBean;
import com.example.moni3.presenter.PresenterImpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity implements Iview{

    private PresenterImpl presenter;
    private ImageView imageView;
    private TextView text_name;
    private Button btn_login;
    private XRecyclerView xRecyclerView;
    private MainAdapter adapter;
    private int page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresenterImpl(this);
        initView();
    }
    private void initView() {
        page = 1;
        imageView = findViewById(R.id.image);
        text_name = findViewById(R.id.text_name);
        btn_login = findViewById(R.id.btn_login);
        xRecyclerView = findViewById(R.id.xrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        xRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        xRecyclerView.addItemDecoration(decoration);
        adapter = new MainAdapter(this);
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
    }

    @Override
    public void getRequest(Object o) {
        if(o instanceof NewsBean){
            NewsBean bean = (NewsBean) o;
            Log.i("tag",bean.getData()+"");

            if(page == 1){
                adapter.setData(bean.getData());
            }else{
                adapter.addData(bean.getData());
            }
            page++;
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();
        }
    }
    public String URL_NEWS ="http://www.xieast.com/api/news/news.php";
    private void initData() {
        presenter.startRequest(URL_NEWS+page,null,NewsBean.class);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
