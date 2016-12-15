package com.example.admin.rxmvvmandretrofitexample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.admin.rxmvvmandretrofitexample.R;
import com.example.admin.rxmvvmandretrofitexample.adapter.FuLiAdapterMvp;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.FuLiMvpPresenterImplDagger2;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.FuLiMvpViewDagger2;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2M.Dagger2Model;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2M.DaggerDagger2Component;
import com.example.admin.rxmvvmandretrofitexample.retrofit.NetServer;
import com.example.admin.rxmvvmandretrofitexample.utils.DividerItemDecoration;
import com.example.admin.rxmvvmandretrofitexample.utils.RecyclerViewUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * mvp+dagger2（注入网络请求(retrofit)）
 */
public class FuLiActivityMvpDagger2M extends AppCompatActivity implements FuLiMvpViewDagger2 {
    public static String TAG = "FuLiActivityMvpDagger2M";
    private RecyclerView recyclerView;
    private Button run;
    @Inject
    FuLiAdapterMvp adapterMvp;
    @Inject
    FuLiMvpPresenterImplDagger2 presenter;
    @Inject
    NetServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_li_mvp_dagger2m);
        recyclerView = (RecyclerView) findViewById(R.id.show_rclv_mvp_dagger2m);
        run = (Button) findViewById(R.id.run_btn_mvp_dagger2m);
        recyclerView.addItemDecoration(DividerItemDecoration.newVertical(this, R.dimen.list_divider_height, R.color.divider_color));
        run.setOnClickListener(view -> presenter.getData(server));
        DaggerDagger2Component.builder().dagger2Model(new Dagger2Model(this, this)).build().inject(this);
        RecyclerViewUtils.setLinearManagerAndAdapter(recyclerView, adapterMvp);
    }

    @Override
    public void getDataSuccess(List<FuliBean> data) {
        adapterMvp.appendItems(data);
        Log.e(TAG, "getDataSuccess: " + "获取数据成功：" + data.size());
    }
}
