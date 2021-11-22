package com.rightcode.camver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightcode.ai_workplace_accident.network.model.request.result.Product_detail;
import com.rightcode.ai_workplace_accident.network.model.request.result.images;
import com.rightcode.camver.R;
import com.rightcode.camver.databinding.ItemViewpageImageBinding;

import java.util.ArrayList;

public class CampingCarDetailViewPagerAdapter extends RecyclerView.Adapter<CampingCarDetailViewPagerAdapter.ViewHolder> {

    Context mContext;
    ArrayList<images> data;

    public CampingCarDetailViewPagerAdapter(Context mContext, ArrayList<images> data) {
        this.mContext = mContext;
        this.data = data;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpage_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ViewHolder extends CommonViewHolder<ItemViewpageImageBinding, images> {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(images item) {
            Glide.with(mContext).load(item.getName()).into(dataBinding.ivThumnail);
        }
    }
}
