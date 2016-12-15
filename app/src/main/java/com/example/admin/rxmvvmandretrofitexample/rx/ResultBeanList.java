package com.example.admin.rxmvvmandretrofitexample.rx;

import com.example.admin.rxmvvmandretrofitexample.model.FuLiResults;

import java.util.List;

import rx.functions.Func1;

/**
 * Created by Admin on 2016/10/28.
 */

public class ResultBeanList implements Func1<FuLiResults, List<FuLiResults.ResultsBean>> {
    @Override
    public List<FuLiResults.ResultsBean> call(FuLiResults fuLiResults) {
        if (!fuLiResults.isError()) {
            return fuLiResults.getResults();
        }
        return null;
    }
}
