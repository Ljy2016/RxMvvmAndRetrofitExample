package com.example.admin.rxmvvmandretrofitexample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.admin.rxmvvmandretrofitexample.R;
import com.example.admin.rxmvvmandretrofitexample.adapter.BaseAdapter;
import com.example.admin.rxmvvmandretrofitexample.adapter.FuLiAdapterMvp;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.FuLiMvpPresenterImplDagger2;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.FuLiMvpViewDagger2;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2S.DaggerPresenterComponent;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2S.PresenterModel;
import com.example.admin.rxmvvmandretrofitexample.utils.DividerItemDecoration;
import com.example.admin.rxmvvmandretrofitexample.utils.RecyclerViewUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * mvp+dagger2（简单引入，不包括网络请求与Observable的依赖注入）
 */
public class FuLiActivityMvpDagger2S extends AppCompatActivity implements FuLiMvpViewDagger2 {

    private RecyclerView recyclerView;
    private Button run;
    private BaseAdapter<FuliBean> adapterMvp;
    @Inject
    FuLiMvpPresenterImplDagger2 presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_li_mvp_dagger2);
        recyclerView = (RecyclerView) findViewById(R.id.show_rclv_mvp_dagger2);
        run = (Button) findViewById(R.id.run_btn_mvp_dagger2);
        recyclerView.addItemDecoration(DividerItemDecoration.newVertical(this, R.dimen.list_divider_height, R.color.divider_color));
        adapterMvp = new FuLiAdapterMvp(this);
        RecyclerViewUtils.setLinearManagerAndAdapter(recyclerView, adapterMvp);
        run.setOnClickListener(view -> presenter.getData());
        DaggerPresenterComponent.builder().presenterModel(new PresenterModel(this)).build().inject(this);
    }

    @Override
    public void getDataSuccess(List<FuliBean> data) {
        adapterMvp.appendItems(data);
    }
}
