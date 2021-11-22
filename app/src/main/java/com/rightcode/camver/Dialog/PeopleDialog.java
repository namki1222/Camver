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

import lombok.Setter;

public class PeopleDialog extends BottomSheetDialogFragment {

    Context mContext;
    DialogPeopleFilterBinding binding;

    @Setter
    OnSaveListener listener;


    public PeopleDialog(Context mContext) {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_people_filter, container, false);

        binding.tvStart.setOnClickListener(view->{
            dismiss();
        });
        return binding.getRoot();
    }
    public interface OnSaveListener {
        void onSave(String name,boolean typeNew,boolean typeDifferent,boolean typeOnly,boolean typeForeign);
    }
}
