package com.example.admin.rxmvvmandretrofitexample.mvpdagger2.mvpDagger2M;

import android.content.Context;

import com.example.admin.rxmvvmandretrofitexample.adapter.BaseAdapter;
import com.example.admin.rxmvvmandretrofitexample.adapter.FuLiAdapterMvp;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.FuLiMvpViewDagger2;
import com.example.admin.rxmvvmandretrofitexample.retrofit.NetServer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Ljy on 2016/11/8.
 * 邮箱：enjoy_azad@sina.com
 */
@Module
public class Dagger2Model {
    private static final String APIURL = "http://gank.io/api/";
    FuLiMvpViewDagger2 fuLiMvpView;
    Context context;

    public Dagger2Model(FuLiMvpViewDagger2 fuLiMvpView, Context context) {
        this.fuLiMvpView = fuLiMvpView;
        this.context = context;
    }

    @Provides
    NetServer providRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://gank.io/api/").build();
        NetServer server = retrofit.create(NetServer.class);
        return server;
    }

    @Provides
    FuLiMvpViewDagger2 provideFuLiMapView() {
        return fuLiMvpView;
    }

    @Provides
    FuLiAdapterMvp provideAdapter() {
        return new FuLiAdapterMvp(context);
    }
}
