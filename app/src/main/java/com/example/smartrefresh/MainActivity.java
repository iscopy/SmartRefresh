package com.example.smartrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_refresh)
    RecyclerView rvRefresh;
    @BindView(R.id.srl_control)
    SmartRefreshLayout srlControl;

    //RecyclerView 数据适配器
    private RefreshAdapter refreshAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerRefresh(Data());
        smartRefresh();

    }

    //适配数据
    public void recyclerRefresh(List<String> Data){
        refreshAdapter = new RefreshAdapter(this, Data, ints -> Toast.makeText(MainActivity.this, Data.get(ints), Toast.LENGTH_SHORT).show());
        rvRefresh.setLayoutManager(new GridLayoutManager(this, 1));
        rvRefresh.setAdapter(refreshAdapter);
        rvRefresh.setNestedScrollingEnabled(false);
    }

    //监听下拉和上拉状态
    public void smartRefresh(){
        //下拉刷新
        srlControl.setOnRefreshListener(refreshlayout -> {
            srlControl.setEnableRefresh(true);//启用刷新
            /**
             * 正常来说，应该在这里加载网络数据
             * 这里我们就使用模拟数据 Data() 来模拟我们刷新出来的数据
             */
            refreshAdapter.strList.clear();
            refreshAdapter.refreshData(Data());
            srlControl.finishRefresh();//结束刷新
        });
        //上拉加载
        srlControl.setOnLoadmoreListener(refreshlayout -> {
            srlControl.setEnableLoadmore(true);//启用加载
            /**
             * 正常来说，应该在这里加载网络数据
             * 这里我们就使用模拟数据 MoreDatas() 来模拟我们加载出来的数据
             */
            refreshAdapter.loadMore(MoreDatas());
            srlControl.finishLoadmore();//结束加载
        });
    }

    //初始数据
    public List<String> Data(){
        List<String> data = new ArrayList<>();
        String temp = "item ";
        for(int i = 0; i < 10; i++) {
            data.add(temp + i);
        }
        return data;
    }

    //刷新得到的数据
    private List<String> MoreDatas() {
        List<String> data = new ArrayList<>();
        String temp = "新加数据";
        for(int i = 0; i < 10; i++) {
            data.add(temp + i);
        }
        return data;
    }
}
