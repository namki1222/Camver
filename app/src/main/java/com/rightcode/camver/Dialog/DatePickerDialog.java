package com.rightcode.camver.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rightcode.camver.R;
import com.rightcode.camver.Util.Log;
import com.rightcode.camver.adapter.CalendarDialogRecyclerViewAdapter;
import com.rightcode.camver.databinding.DialogDatePickerBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import kotlin.Unit;
import lombok.Getter;
import lombok.Setter;

public class DatePickerDialog extends BottomSheetDialogFragment {

    private final String EMPTY = "empty";

    Context mContext;
    DialogDatePickerBinding binding;
    GregorianCalendar mCalendar;

    SimpleDateFormat sdf;

    @Setter
    Date limit;


    OnSelectDateListener listener;
    CalendarDialogRecyclerViewAdapter adapter;

    public DatePickerDialog(Context mContext) {
        this.mContext = mContext;
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_date_picker, container, false);
        initialize();

        return binding.getRoot();
    }

    private void initialize() {
        if (mCalendar == null) {
            mCalendar = new GregorianCalendar();
            mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        }

        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy.M");
        }

        binding.ivPrevious.setOnClickListener(view -> {
            previousMonth();
            adapter.notifyDataSetChanged();
        });

        binding.ivNext.setOnClickListener(view -> {
            nextMonth();
            adapter.notifyDataSetChanged();
        });
        binding.tvCancel.setOnClickListener(view->{
            dismiss();
        });
        binding.tvStart.setOnClickListener(view->{
            listener.onSelect(adapter.getGetSelectedDate3());
            dismiss();
        });

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        binding.rvSchedule.setLayoutManager(layoutManager);
        adapter = new CalendarDialogRecyclerViewAdapter(mContext);
        adapter.setLimit(limit);
        binding.rvSchedule.setAdapter(adapter);

        binding.tvMonth.setText(sdf.format(mCalendar.getTime()));

        connectAdapter();

    }

    private String toDate(String date) {
        try {
            Log.d(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.parse(date);
            String day = new SimpleDateFormat("yyyy-MM-dd").format(sdf);
            return day;
        } catch (Exception e) {
            return null;
        }
    }
    public void nextMonth() {
        mCalendar.add(Calendar.MONTH, 1);
        binding.tvMonth.setText(sdf.format(mCalendar.getTime()));
        connectAdapter();
    }

    public void previousMonth() {
        mCalendar.add(Calendar.MONTH, -1);
        binding.tvMonth.setText(sdf.format(mCalendar.getTime()));
        connectAdapter();
    }

    private ArrayList<Object> getCalendar() {
        ArrayList<Object> calendarList = new ArrayList<>();

        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK)-1;
        int max = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < dayOfWeek; i++) {
            calendarList.add(EMPTY);
        }

        for (int i = 1; i <= max; i++) {
            calendarList.add(new GregorianCalendar(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), i));
        }

        return calendarList;
    }

    private void connectAdapter() {
        adapter.setCalendar(getCalendar());
        adapter.notifyDataSetChanged();
    }
    public interface OnSelectDateListener {
        void onSelect(String date);
    }

    public void setListener(OnSelectDateListener listener) {
        this.listener = listener;
    }
}
