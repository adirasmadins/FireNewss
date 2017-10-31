package com.leyifu.firenewss.view.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leyifu.firenewss.R;
import com.leyifu.firenewss.adapter.VideoAdapter;
import com.leyifu.firenewss.view.fragment.video.FunnyFragment;
import com.leyifu.firenewss.view.fragment.video.GoodFragment;
import com.leyifu.firenewss.view.fragment.video.HotFragment;
import com.leyifu.firenewss.view.fragment.video.RecreationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    @BindView(R.id.video_tab_layout)
    TabLayout videoTabLayout;
    @BindView(R.id.video_view_pager)
    ViewPager videoViewPager;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    Unbinder unbinder;

    List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        titles.add("热点");
        titles.add("搞笑");
        titles.add("娱乐");
        titles.add("精品");

        fragments.add(HotFragment.getInstance());
        fragments.add(FunnyFragment.getInstance());
        fragments.add(RecreationFragment.getInstance());
        fragments.add(GoodFragment.getInstance());

        videoTabLayout.setTag(videoTabLayout.newTab().setText(titles.get(0)));
        videoTabLayout.setTag(videoTabLayout.newTab().setText(titles.get(1)));
        videoTabLayout.setTag(videoTabLayout.newTab().setText(titles.get(2)));
        videoTabLayout.setTag(videoTabLayout.newTab().setText(titles.get(3)));

        videoViewPager.setAdapter(new VideoAdapter(getActivity().getSupportFragmentManager(),titles,fragments));
        videoViewPager.setOffscreenPageLimit(4);
        videoTabLayout.setupWithViewPager(videoViewPager);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.floatingActionButton)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floatingActionButton:

                break;
            default:
                break;
        }
    }
}
