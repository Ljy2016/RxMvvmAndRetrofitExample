package com.example.admin.rxmvvmandretrofitexample.mvp;

import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;

import java.util.List;

/**
 * Created by Admin on 2016/11/3.
 */

public interface FuLiMvpView {


    void getDataSuccess(List<FuliBean> data);
}
