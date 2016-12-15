package com.example.admin.rxmvvmandretrofitexample.mvp;

import android.util.Log;

import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.net.FULi;

import java.util.List;

/**
 * Created by Admin on 2016/11/3.
 */

public class FuLiMvpPresenterImpl implements FuLiMvpPresenter {
    FuLiMvpView mvpView;

    public FuLiMvpPresenterImpl(FuLiMvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void getData() {
        //rx+retrofit
        FULi.MvpGetFuli(mvpView);
    }
}
