package com.rightcode.camver.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.rightcode.camver.Activity.BaseActivity;
import com.rightcode.camver.Dialog.CommonTextDialog;
import com.rightcode.camver.Util.Log;
import com.rightcode.camver.network.ApiResponseHandler;
import com.rightcode.camver.network.model.response.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class CommonViewHolder<B extends ViewDataBinding, I> extends RecyclerView.ViewHolder {

    protected B dataBinding;
    private CommonTextDialog errorDialog;

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
    public <Q extends CommonResponse> void callApi(Call<Q> call, ApiResponseHandler<Q> handler) {
        Callback<Q> callback = new Callback<Q>() {
            @Override
            public void onResponse(Call<Q> call, Response<Q> response) {
                Q result = response.body();
                if (response.code() == 200 || response.code() == 201) {
                    handler.onSuccess(result);
                } else if (response.code() > 201) {
                    if (response.body() != null) {
                        CommonResponse error = response.body();
                        handler.onServerFail(error);
                        return;
                    }
                    if (response.errorBody() == null) {
                        showErrorDialog("에러");
                        return;
                    }
                    try {
                        Gson gson = new Gson();
                        CommonResponse error = gson.fromJson(response.errorBody().string(), CommonResponse.class);
                        handler.onServerFail(error);
                    } catch (Exception e) {
                        Log.e(e.getMessage());
                    }
                } else {
                    handler.onNoResponse(result);
                }
            }

            @Override
            public void onFailure(Call<Q> call, Throwable t) {
                Log.e(t.getMessage());
                handler.onBadRequest(t);
            }
        };

        call.enqueue(callback);
    }
    public void showErrorDialog(String message) {
        showErrorDialog(message, v -> errorDialog.dismiss());
    }

    public void showErrorDialog(String message, View.OnClickListener listener) {
        if (errorDialog == null) {
            errorDialog = new CommonTextDialog(errorDialog.getActivity()
            );
        }

        errorDialog.setTitle("에러");
        errorDialog.setMessage(message);
        errorDialog.setPositiveButton("확인", listener);

        if (errorDialog != null && !errorDialog.isAdded()) {
            errorDialog.show();
        }
    }
}
