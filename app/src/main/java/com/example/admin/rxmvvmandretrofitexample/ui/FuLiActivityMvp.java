package com.example.admin.rxmvvmandretrofitexample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.admin.rxmvvmandretrofitexample.R;
import com.example.admin.rxmvvmandretrofitexample.adapter.BaseAdapter;
import com.example.admin.rxmvvmandretrofitexample.adapter.FuLiAdapterMvp;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.mvp.FuLiMvpPresenter;
import com.example.admin.rxmvvmandretrofitexample.mvp.FuLiMvpPresenterImpl;
import com.example.admin.rxmvvmandretrofitexample.mvp.FuLiMvpView;
import com.example.admin.rxmvvmandretrofitexample.utils.DividerItemDecoration;
import com.example.admin.rxmvvmandretrofitexample.utils.RecyclerViewUtils;

import java.util.List;


/**
 * Created by Ljy on 2016/11/3.
 * Mvp+Rx+Retrofit
 */

public class FuLiActivityMvp extends AppCompatActivity implements FuLiMvpView {
    private RecyclerView recyclerView;
    private Button run;
    private FuLiMvpPresenter fuLiMvpPresenter;
    private BaseAdapter<FuliBean> adapterMvp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli_mvp);
        recyclerView = (RecyclerView) findViewById(R.id.show_rclv_mvp);
        run = (Button) findViewById(R.id.run_btn_mvp);
        init();
    }

    private void init() {
        recyclerView.addItemDecoration(DividerItemDecoration.newVertical(this, R.dimen.list_divider_height, R.color.divider_color));
        fuLiMvpPresenter = new FuLiMvpPresenterImpl(this);
        adapterMvp = new FuLiAdapterMvp(this);
        RecyclerViewUtils.setLinearManagerAndAdapter(recyclerView, adapterMvp);
        //点击事件，通过presenter获取数据
        run.setOnClickListener(view -> fuLiMvpPresenter.getData());
    }

    //成功回调，显示数据
    @Override
    public void getDataSuccess(List<FuliBean> data) {
        adapterMvp.appendItems(data);
    }
}
