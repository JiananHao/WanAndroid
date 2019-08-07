package com.example.wanandroid.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.wanandroid.R;

public class LoadingDialog extends Dialog {

    private View view;
    private TextView textView;
    static LoadingDialog loadingDialog;
    Context context;

    public static LoadingDialog getInstance(Context context){

        if (loadingDialog == null){
            loadingDialog = new LoadingDialog(context);
        }
        return loadingDialog;

    }

    private LoadingDialog(Context context) {
        super(context, R.style.Theme_AppCompat_DayNight_Dialog);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
        this.context = context;
        view = getLayoutInflater().inflate(R.layout.loading_dialog,null);
        textView = view.findViewById(R.id.tv);
        this.setContentView(view);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (loadingDialog != null){
            loadingDialog = null;
        }
    }
}
