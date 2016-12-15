package com.example.admin.rxmvvmandretrofitexample.ui;


import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.admin.rxmvvmandretrofitexample.R;
import com.example.admin.rxmvvmandretrofitexample.adapter.BaseAdapter;
import com.example.admin.rxmvvmandretrofitexample.adapter.FuLiAdapter;
import com.example.admin.rxmvvmandretrofitexample.model.FuLiResults;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.net.FULi;
import com.example.admin.rxmvvmandretrofitexample.retrofit.NetServer;
import com.example.admin.rxmvvmandretrofitexample.utils.DividerItemDecoration;
import com.example.admin.rxmvvmandretrofitexample.utils.RecyclerViewUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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
 * Mvvm+DataBinding获取图片并加载
 */

public class FuLiActivityMvvM extends AppCompatActivity {
    private final static String TAG = "FuLiActivityMvvM";
    private RecyclerView recyclerView;
    private BaseAdapter<FuliBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.show_rclv);
        recyclerView.addItemDecoration(DividerItemDecoration.newVertical(this,
                R.dimen.list_divider_height, R.color.divider_color));
        adapter = new FuLiAdapter(this);
        RecyclerViewUtils.setLinearManagerAndAdapter(recyclerView, adapter);
    }

    public void go(View view) {
        adapter.removeAll();
        //获取数据并显示
        FULi.MvvmGetFuli(adapter);
    }

    //根据网址爬图片
    public void go1(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<FuliBean> list = new ArrayList<>();
                int i = 0;
                try {
                    Document document = Jsoup.connect("http://www.netbian.com/").get();
//                  String title1 = document.title();
                    Elements pictureUrls = document.select("img[src~=(?i).(png|jpe?g)]");
//                  Elements pictureUrls = document.select("link");
                    Log.e(TAG, "run: " + pictureUrls.size());
                    for (Element element : pictureUrls) {
//                        if (i < 10) {
                        FuliBean bean = new FuliBean();
                        bean.setUrl(element.attr("src"));
                        Log.e(TAG, "run: " + bean.getUrl());
                        bean.setData(++i + "");
                        list.add(bean);
//                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.appendItems(list);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
