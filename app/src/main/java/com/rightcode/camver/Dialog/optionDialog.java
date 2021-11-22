package com.rightcode.camver.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rightcode.ai_workplace_accident.network.model.request.result.option_data;
import com.rightcode.ai_workplace_accident.network.model.request.result.options;
import com.rightcode.camver.R;
import com.rightcode.camver.Util.Log;
import com.rightcode.camver.adapter.OptionRecyclerViewAdapter;
import com.rightcode.camver.databinding.DialogOptionBinding;
import com.rightcode.camver.databinding.DialogPeopleFilterBinding;

import java.util.ArrayList;

import lombok.Setter;

public class optionDialog extends BottomSheetDialogFragment {

    Context mContext;
    DialogOptionBinding binding;

    ArrayList<options> options = null;
    ArrayList<option_data> data = null;

    OptionRecyclerViewAdapter rv_adapter = null;
    payListener listener;

    int count_data = 1;

    public optionDialog(Context mContext) {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_option, container, false);
        data = new ArrayList<>();
        String[] items = new String[0];
        if (options.size() != 0) {
            items = new String[options.size() + 1];
            items[0] = "선택";
            for (int count = 0; count < options.size(); count++) {
                items[count + 1] = options.get(0).getCategories().get(count).getName();
                Log.d(items[count + 1]);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mContext, android.R.layout.simple_spinner_item, items);


        rv_adapter = new OptionRecyclerViewAdapter(mContext, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        binding.rvList.setLayoutManager(layoutManager);
        binding.rvList.setAdapter(rv_adapter);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Log.d("!!!!!");
                        for (int count = 0; count < data.size(); count++) {
                            if (data.get(count).getTitle().equals(options.get(0).getCategories().get(position - 1).getName())) {
                                count_data = data.get(count).getCount() + 1;
                                data.get(count).setCount(data.get(count).getCount() + 1);
                            }
                        }
                        if (count_data != 1) {
                            connectAdapter(data);
                        } else {
                            option_data data_on = new option_data(options.get(0).getCategories().get(position - 1).getName(), options.get(0).getCategories().get(position - 1).getPrice(), count_data);
                            data.add(data_on);
                            connectAdapter(data);
                        }
                        binding.spinner.setSelection(0);
                        Integer total_money = 0;
                        for (int count = 0; count < data.size(); count++) {
                            total_money = (data.get(count).getCount() * data.get(count).getPrice());
                        }
                        binding.tvMoney.setText(total_money.toString() + "원");
                        count_data = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rv_adapter.setListener(new OptionRecyclerViewAdapter.RemoveOnclick() {
            @Override
            public void onClick(int position) {
                data.get(position).setCount(data.get(position).getCount() - 1);
                if (data.get(position).getCount() == 0) {
                    data.remove(position);
                }
                connectAdapter(data);
                Integer total_money = 0;
                if(data.isEmpty()){

                    binding.tvMoney.setText(total_money.toString()+"원");
                    count_data = 1;
                }else{
                    for (int count = 0; count < data.size(); count++) {
                        total_money = (data.get(count).getCount() * data.get(count).getPrice());
                    }
                    binding.tvMoney.setText(total_money.toString() + "원");
                    count_data = 1;
                }
            }
        });
        rv_adapter.setListener_add(new OptionRecyclerViewAdapter.AddOnclick() {
            @Override
            public void onClick(int position) {
                data.get(position).setCount(data.get(position).getCount() + 1);
                connectAdapter(data);
                Integer total_money = 0;
                if(data.isEmpty()){

                    binding.tvMoney.setText(total_money.toString()+"원");
                    count_data = 1;
                }else{
                    for (int count = 0; count < data.size(); count++) {
                        total_money = (data.get(count).getCount() * data.get(count).getPrice());
                    }
                    binding.tvMoney.setText(total_money.toString() + "원");
                    count_data = 1;
                }
            }
        });
        rv_adapter.setListener_exit(new OptionRecyclerViewAdapter.ExitOnclick() {
            @Override
            public void onClick(int position) {
                data.remove(position);
                connectAdapter(data);
                Integer total_money = 0;
                if(data.isEmpty()){

                    binding.tvMoney.setText(total_money.toString()+"원");
                    count_data = 1;
                }else{
                    for (int count = 0; count < data.size(); count++) {
                        total_money = (data.get(count).getCount() * data.get(count).getPrice());
                    }
                    binding.tvMoney.setText(total_money.toString() + "원");
                    count_data = 1;
                }

            }
        });

        binding.tvStart.setOnClickListener(view -> {
            listener.next_event(data);
            dismiss();
        });
        return binding.getRoot();
    }

    public interface payListener {
        void next_event(ArrayList<option_data> data);
    }

    private void connectAdapter(ArrayList<option_data> data) {
        rv_adapter.setData(data);
        rv_adapter.notifyDataSetChanged();
    }

    public void setListener(payListener listener) {
        this.listener = listener;
    }

    public void setoptions(ArrayList<options> options) {
        this.options = options;
    }
}
