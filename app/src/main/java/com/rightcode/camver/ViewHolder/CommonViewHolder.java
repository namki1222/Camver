package com.rightcode.camver.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rightcode.camver.Activity.BaseActivity;


public abstract class CommonViewHolder<B extends ViewDataBinding, I> extends RecyclerView.ViewHolder {

    protected B dataBinding;

    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);
        dataBinding = DataBindingUtil.bind(itemView);
    }

    public abstract void onBind(I item);

    protected void startActivity(Context context, Intent intent) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivity(intent);
        }
    }
}
