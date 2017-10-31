package com.leyifu.firenewss.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leyifu.firenewss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GirlPictureActivity extends BaseActivity {


    @BindView(R.id.iv_gril_picture)
    ImageView ivGrilPicture;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_picture);
        ButterKnife.bind(this);

        init();
    }

    @Override
    public int getStaueColor() {
        return R.color.black;
    }

    private void init() {
        Intent intent = getIntent();
        String pic_url = intent.getStringExtra("pic_url");
        Glide.with(this).load(pic_url).into(ivGrilPicture);
    }

    @OnClick({R.id.iv_back, })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

        }
    }
}
