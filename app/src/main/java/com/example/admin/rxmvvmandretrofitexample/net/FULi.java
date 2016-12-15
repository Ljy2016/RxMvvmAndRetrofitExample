package com.example.admin.rxmvvmandretrofitexample.net;

import android.util.Log;

import com.example.admin.rxmvvmandretrofitexample.adapter.BaseAdapter;
import com.example.admin.rxmvvmandretrofitexample.model.FuLiResults;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.mvp.FuLiMvpView;
import com.example.admin.rxmvvmandretrofitexample.mvpdagger2.FuLiMvpViewDagger2;
import com.example.admin.rxmvvmandretrofitexample.retrofit.NetServer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ljy on 2016/11/3.
 */

public class FULi {
    public static String TAG = "FULi";
    private static NetServer server;
    private static int i = 1;

    public static void MvvmGetFuli(BaseAdapter<FuliBean> adapter) {
        GETDATA(list -> adapter.appendItems(list));
    }

    public static void MvpGetFuli(FuLiMvpView fuLiMvpView) {
        GETDATA(list -> fuLiMvpView.getDataSuccess(list));
    }

    public static void MvpGetFuliDagger2(FuLiMvpViewDagger2 fuLiMvpView) {
        Log.e(TAG, "MvpGetFuliDagger2: " + "开始获取数据");
        GETDATA(list -> fuLiMvpView.getDataSuccess(list));
    }

    public static void GETDATA(GetFuLiDataListener getFuLiDataListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://gank.io/api/").build();
        server = retrofit.create(NetServer.class);
        Observable<FuLiResults> observable = server.getFuLi(20, i);
        i++;
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
        }).subscribe((fulibeans) -> {
            getFuLiDataListener.success(fulibeans);
            Log.e(TAG, "GETDATA: " + "获取数据成功");
        });
    }

    interface GetFuLiDataListener {
        void success(List<FuliBean> list);
    }

}
