package com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2S;

import com.example.admin.rxmvvmandretrofitexample.mvp.FuLiMvpView;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.FuLiMvpViewDagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ljy on 2016/11/5.
 */
@Module
public class PresenterModel {
    private final FuLiMvpViewDagger2 fuLiMvpView;

    public PresenterModel(FuLiMvpViewDagger2 fuLiMvpView) {
        this.fuLiMvpView = fuLiMvpView;
    }

    @Provides
    FuLiMvpViewDagger2 providerFuLiMapView() {
        return fuLiMvpView;
    }
}
