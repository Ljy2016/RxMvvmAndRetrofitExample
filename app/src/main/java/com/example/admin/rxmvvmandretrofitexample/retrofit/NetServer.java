package com.example.admin.rxmvvmandretrofitexample.retrofit;

import com.example.admin.rxmvvmandretrofitexample.model.FuLiResults;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Admin on 2016/10/28.
 */

public interface NetServer {
    @GET("data/福利/{number}/{page}")
    Observable<FuLiResults> getFuLi(@Path("number") int count, @Path("page") int page);

}
