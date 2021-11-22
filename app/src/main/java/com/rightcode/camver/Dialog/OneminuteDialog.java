package com.rightcode.camver.Dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.rightcode.camver.R;
import com.rightcode.camver.databinding.DialogOneMinuteBinding;


public class OneminuteDialog extends Activity {
    DialogOneMinuteBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.dialog_one_minute);
        dataBinding.tvConfirm.setOnClickListener(view->{
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}