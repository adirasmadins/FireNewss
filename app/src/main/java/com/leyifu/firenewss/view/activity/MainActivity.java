package com.leyifu.firenewss.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.leyifu.firenewss.R;
import com.leyifu.firenewss.view.fragment.AttentionFragment;
import com.leyifu.firenewss.view.fragment.BelleFragment;
import com.leyifu.firenewss.view.fragment.HomeFragment;
import com.leyifu.firenewss.view.fragment.VideoFragment;
import com.leyifu.firenewss.view.widget.ExitDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_belle)
    RadioButton rbBelle;
    @BindView(R.id.rb_video)
    RadioButton rbVideo;
    @BindView(R.id.rb_attention)
    RadioButton rbAttention;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.main_view_pager)
    FrameLayout frameLayout;

    private HomeFragment homeFragment;
    private BelleFragment belleFragment;
    private VideoFragment videoFragment;
    private AttentionFragment attentionFragment;

    private Fragment currentFragment;

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    @Override
    public int getStaueColor() {
        return R.color.orange_400;
    }

    private void init() {

        initDrawable();

        initFragment();

        radioGroup.setOnCheckedChangeListener(checkChangeListener);
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        belleFragment = new BelleFragment();
        videoFragment = new VideoFragment();
        attentionFragment = new AttentionFragment();

        fragments.add(homeFragment);
        fragments.add(belleFragment);
        fragments.add(videoFragment);
        fragments.add(attentionFragment);
        fragmentManager = getSupportFragmentManager();
        if (!fragments.get(0).isAdded()) {
            fragmentManager.beginTransaction().add(R.id.main_view_pager, fragments.get(0)).commit();
            currentFragment = fragments.get(0);
        }

    }

    /**
     * 设置radiobutton图片大小
     */
    private void initDrawable() {
        Drawable drawableHome = getResources().getDrawable(R.drawable.main_home);
        Drawable drawableBelle = getResources().getDrawable(R.drawable.main_belle);
        Drawable drawableVideo = getResources().getDrawable(R.drawable.main_video);
        Drawable drawableAttention = getResources().getDrawable(R.drawable.main_attention);

        drawableHome.setBounds(0, 0, 40, 40);
        drawableBelle.setBounds(0, 0, 40, 40);
        drawableVideo.setBounds(0, 0, 40, 40);
        drawableAttention.setBounds(0, 0, 40, 40);

        rbHome.setCompoundDrawables(null, drawableHome, null, null);
        rbBelle.setCompoundDrawables(null, drawableBelle, null, null);
        rbVideo.setCompoundDrawables(null, drawableVideo, null, null);
        rbAttention.setCompoundDrawables(null, drawableAttention, null, null);
    }

    RadioGroup.OnCheckedChangeListener checkChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.rb_home:
                    showOrAddFragment(fragments.get(0));
                    break;
                case R.id.rb_belle:
                    showOrAddFragment(fragments.get(1));
                    break;
                case R.id.rb_video:
                    showOrAddFragment(fragments.get(2));
                    break;
                case R.id.rb_attention:
                    showOrAddFragment(fragments.get(3));
                    break;
                default:
                    break;
            }
        }
    };

    private void showOrAddFragment(Fragment fragment) {
        if (fragment == currentFragment) {
            return;
        }
        /**
         * 此处必须要fragmentManager.beginTransaction() 不能使用全局的fragmentManager.beginTransaction()
         * 否侧报错  commit already called
         *
         */
        if (!fragment.isAdded()) {
            fragmentManager.beginTransaction().hide(currentFragment).add(R.id.main_view_pager, fragment).commit();
        } else {
            fragmentManager.beginTransaction().hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) && event.getRepeatCount() == 0) {
            new ExitDialog(MainActivity.this,R.style.ExitDialog).show();
        }
        return false;
    }
}
