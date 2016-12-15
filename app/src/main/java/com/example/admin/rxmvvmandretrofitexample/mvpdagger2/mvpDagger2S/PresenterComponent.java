package com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2S;

import com.example.admin.rxmvvmandretrofitexample.ui.FuLiActivityMvpDagger2S;

import dagger.Component;

/**
 * Created by Admin on 2016/11/5.
 */
@Component(modules = PresenterModel.class)
public interface PresenterComponent {
    void inject(FuLiActivityMvpDagger2S activity);
}
