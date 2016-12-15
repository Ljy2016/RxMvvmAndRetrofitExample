package com.example.admin.rxmvvmandretrofitexample.mvpdagger2;

import com.example.admin.rxmvvmandretrofitexample.model.FuLiResults;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.mvp.FuLiMvpPresenter;
import com.example.admin.rxmvvmandretrofitexample.mvp.FuLiMvpView;
import com.example.admin.rxmvvmandretrofitexample.net.FULi;
import com.example.admin.rxmvvmandretrofitexample.retrofit.NetServer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin on 2016/11/3.
 */

public class FuLiMvpPresenterImplDagger2 implements FuLiMvpPresenter {
    @Inject
    FuLiMvpViewDagger2 mvpView;

    @Inject
    public FuLiMvpPresenterImplDagger2() {

    }

    @Override
    public void getData() {
        FULi.MvpGetFuliDagger2(mvpView);
    }

    /**
     * for FuLiActivityMvpDagger2M NetServer
     *
     * @param server 通过dagger2注入的server
     */
    public void getData(NetServer server) {
        Observable<FuLiResults> observable = server.getFuLi(20, 1);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map((fuLiResults) -> {
            if (!fuLiResults.isError()) {
                List<FuliBean> fuliBeanList = new ArrayList<>();
                for (FuLiResults.ResultsBean rBean : fuLiResults.getResults()) {
                    FuliBean bean = new FuliBean();
                    bean.setData(rBean.getCreatedAt());
                    bean.setUrl(rBean.getUrl());
                    fuliBeanList.add(bean);
                }
                return fuliBeanList;
            }
            return null;
        }).subscribe(
                (fuliBeanList) -> {
                    mvpView.getDataSuccess(fuliBeanList);
                }
        );
    }
}
