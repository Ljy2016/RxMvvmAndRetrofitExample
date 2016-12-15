package com.example.admin.rxmvvmandretrofitexample.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.rxmvvmandretrofitexample.BR;
import com.example.admin.rxmvvmandretrofitexample.model.FuliBean;
import com.example.admin.rxmvvmandretrofitexample.R;
import com.example.admin.rxmvvmandretrofitexample.databinding.FuliItemBinding;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;

/**
 * Created by Admin on 2016/10/29.
 */

public class FuLiAdapter extends BaseAdapter<FuliBean> {


    public FuLiAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onMyCreateViewHolder(ViewGroup parent, int viewType) {
        FuliItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.fuli_item, parent, false);
        FuLiViewHolder holder = new FuLiViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onMyBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        FuLiViewHolder holder = (FuLiViewHolder) viewHolder;
        FuliBean fuliBean = getModel(position);
        holder.getBinding().setVariable(BR.fuli, fuliBean);
        holder.getBinding().executePendingBindings();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(fuliBean.getUrl())
                .setAutoPlayAnimations(true)
                .build();
        holder.getBinding().fuliIv.setController(controller);
    }

    @Override
    public int getMyItemViewType(int position) {
        return 0;
    }

    public class FuLiViewHolder extends RecyclerView.ViewHolder {

        public FuLiViewHolder(View itemView) {
            super(itemView);
        }

        FuliItemBinding binding;

        public FuliItemBinding getBinding() {
            return binding;
        }

        public void setBinding(FuliItemBinding binding) {
            this.binding = binding;
        }

    }
}
