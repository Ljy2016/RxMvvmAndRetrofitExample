package com.example.admin.rxmvvmandretrofitexample.progress;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Admin on 2016/10/27.
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOWDIALOG = 0;
    public static final int DISMISSDIALOG = 1;
    private ProgressDialog pd;
    private Context context;
    private ProgressCancelListener cancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener cancelListener) {
        this.context = context;
        this.cancelListener = cancelListener;
    }

    private void showDialog() {
        if (pd == null) {
            pd = new ProgressDialog(context);
            pd.setIndeterminate(true);
            pd.setCancelable(true);
            pd.setTitle("正在加载，请稍候");
            pd.setOnCancelListener(dialog -> {
                cancelListener.onCancelProgress();
            });
            pd.show();
        }
    }

    private void dismissDialog() {
        if (pd != null) {
            pd.dismiss();
        }
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case SHOWDIALOG:
                showDialog();
                break;
            case DISMISSDIALOG:
                dismissDialog();
                break;
        }
    }
}
