package com.rightcode.camver.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rightcode.camver.R;
import com.rightcode.camver.databinding.DialogPeopleFilterBinding;
import com.rightcode.camver.databinding.DialogSortFilterBinding;

import lombok.Setter;

public class SortDialog extends BottomSheetDialogFragment {

    Context mContext;
    DialogSortFilterBinding binding;

    OnSaveListener listener;
    public SortDialog(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_sort_filter, container, false);
        binding.llCheck1.setOnClickListener(view->{
            binding.ivCheck1.setSelected(true);
            binding.ivCheck2.setSelected(false);
            binding.ivCheck3.setSelected(false);


            binding.tvCheck1.setSelected(true);
            binding.tvCheck2.setSelected(false);
            binding.tvCheck3.setSelected(false);
            listener.onSave("인기도순");
        });
        binding.llCheck2.setOnClickListener(view->{
            binding.ivCheck1.setSelected(false);
            binding.ivCheck2.setSelected(true);
            binding.ivCheck3.setSelected(false);

            binding.tvCheck1.setSelected(false);
            binding.tvCheck2.setSelected(true);
            binding.tvCheck3.setSelected(false);
            listener.onSave("평점높은순");
        });
        binding.llCheck3.setOnClickListener(view->{
            binding.ivCheck1.setSelected(false);
            binding.ivCheck2.setSelected(false);
            binding.ivCheck3.setSelected(true);

            binding.tvCheck1.setSelected(false);
            binding.tvCheck2.setSelected(false);
            binding.tvCheck3.setSelected(true);
            listener.onSave("최신등록순");

        });
        return binding.getRoot();
    }

    public interface OnSaveListener {
        void onSave(String sort);
    }
    public void setListener(OnSaveListener listener) {
        this.listener = listener;
    }
}
