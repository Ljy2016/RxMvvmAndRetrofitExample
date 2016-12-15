package com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2M;


import com.example.admin.rxmvvmandretrofitexample.ui.FuLiActivityMvpDagger2M;

import dagger.Component;

/**
 * 作者：Ljy on 2016/11/8.
 * 邮箱：enjoy_azad@sina.com
 */
@Component(modules = Dagger2Model.class)
public interface Dagger2Component {
    void inject(FuLiActivityMvpDagger2M activity);
}
