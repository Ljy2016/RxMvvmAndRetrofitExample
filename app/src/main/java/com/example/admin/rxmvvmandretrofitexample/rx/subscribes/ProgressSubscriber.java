package com.example.admin.rxmvvmandretrofitexample.rx.subscribes;

import android.content.Context;


import com.example.admin.rxmvvmandretrofitexample.progress.ProgressCancelListener;
import com.example.admin.rxmvvmandretrofitexample.progress.ProgressDialogHandler;

import rx.Subscriber;

/**
 * Created by Admin on 2016/10/27.
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener<T> subscriberOnNextListener;
    ProgressDialogHandler progressDialogHandler;


    public ProgressSubscriber(Context context, SubscriberOnNextListener<T> subscriberOnNextListener) {
        this.subscriberOnNextListener = subscriberOnNextListener;
        progressDialogHandler = new ProgressDialogHandler(context, this);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        progressDialogHandler.obtainMessage(progressDialogHandler.SHOWDIALOG).sendToTarget();
//    }
//
//    @Override
//    public void onCompleted() {
//        progressDialogHandler.obtainMessage(progressDialogHandler.DISMISSDIALOG).sendToTarget();
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        progressDialogHandler.obtainMessage(progressDialogHandler.DISMISSDIALOG).sendToTarget();
//    }
//
//    @Override
//    public void onNext(Object o) {
//        subscriberOnNextListener.onNext();
//    }

    @Override
    public void onCancelProgress() {
        if (this.isUnsubscribed())
            this.unsubscribe();
    }

    @Override
    public void onStart() {
        super.onStart();
        progressDialogHandler.obtainMessage(progressDialogHandler.SHOWDIALOG).sendToTarget();
    }

    @Override
    public void onCompleted() {
        progressDialogHandler.obtainMessage(progressDialogHandler.DISMISSDIALOG).sendToTarget();
    }

    @Override
    public void onError(Throwable e) {
        progressDialogHandler.obtainMessage(progressDialogHandler.DISMISSDIALOG).sendToTarget();
    }

    @Override
    public void onNext(T t) {
        subscriberOnNextListener.onNext(t);
    }
}
