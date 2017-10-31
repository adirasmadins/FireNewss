package com.leyifu.firenewss.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leyifu.firenewss.R;
import com.leyifu.firenewss.app.MyApplication;

/**
 * @autoor hahaha
 * @TIME 2017/10/30 0030.
 */

public class ExitDialog extends Dialog implements View.OnClickListener{

    private ImageView loading_image;
    private AnimationDrawable animationDrawable;
    private TextView dialog_hint_text_context;
    private TextView dialog_hint_text_yes;
    private TextView dialoh_hint_text_no;

    public ExitDialog(@NonNull Context context) {
        super(context);
    }


    public ExitDialog(Context context, int theme) {
       super(context,theme);
    }


    protected ExitDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_hint);
        initview();
    }

    public void initview() {
        dialog_hint_text_context = (TextView) findViewById(R.id.dialog_hint_text_context);
        dialog_hint_text_yes = (TextView) findViewById(R.id.dialog_hint_text_yes);
        dialoh_hint_text_no = (TextView) findViewById(R.id.dialoh_hint_text_no);
        dialoh_hint_text_no.setOnClickListener(this);
        dialog_hint_text_yes.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialoh_hint_text_no:
                dismiss();
                break;
            case R.id.dialog_hint_text_yes:
                MyApplication.exit();
                dismiss();
                break;
        }
    }
}
