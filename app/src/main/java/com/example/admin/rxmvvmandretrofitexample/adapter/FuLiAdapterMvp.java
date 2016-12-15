package com.example.admin.rxmvvmandretrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.admin.rxmvvmandretrofitexample.R;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Admin on 2016/10/29.
 */

public class FuLiAdapterMvp extends BaseAdapter<FuliBean> {


    public FuLiAdapterMvp(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onMyCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.fuli_item_mvp, parent, false);
        FuLiViewHolder holder = new FuLiViewHolder(sView);
        return holder;
    }

    @Override
    public void onMyBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        FuLiViewHolder holder = (FuLiViewHolder) viewHolder;
        FuliBean fuliBean = getModel(position);
        holder.textView.setText(fuliBean.getData());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(fuliBean.getUrl())
                .setAutoPlayAnimations(true)
                .build();
        holder.imageView.setController(controller);
    }

    @Override
    public int getMyItemViewType(int position) {
        return 0;
    }

    public class FuLiViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageView;
        TextView textView;

        public FuLiViewHolder(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.fuli_iv_mvp);
            textView = (TextView) itemView.findViewById(R.id.fuli_tv_mvp);
        }

    }


}
