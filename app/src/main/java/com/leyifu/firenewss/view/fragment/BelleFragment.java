package com.leyifu.firenewss.view.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.leyifu.firenewss.R;
import com.leyifu.firenewss.adapter.GrilRecyclerAdapter;
import com.leyifu.firenewss.bean.GrilBean;
import com.leyifu.firenewss.interf.ApiInterf;
import com.leyifu.firenewss.interf.IgetGrilPicture;
import com.leyifu.firenewss.persenter.Persenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BelleFragment extends Fragment implements IgetGrilPicture {

    @BindView(R.id.tool_bar_belle)
    Toolbar toolBarBelle;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.recycler_view_belle)
    RecyclerView recyclerViewBelle;
    Unbinder unbinder;
    @BindView(R.id.swipe_rehresh_belle)
    SwipeRefreshLayout swipeRehreshBelle;
    @BindView(R.id.ll_net_error)
    LinearLayout llNetError;
    private View view;

    private static int start = 0;
    private static int refresh = 0;
    private static int count = 20;

    private static final String TAG = "BelleFragment";
    private GridLayoutManager gridLayoutManager;
    private GrilRecyclerAdapter adapter;
    private Persenter persenter;
    private int lastVisibleItemPosition;

    private GrilBean mGrilBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_belle, container, false);
        unbinder = ButterKnife.bind(this, view);

        init();

        return view;
    }


    private void init() {


        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        StaggeredGridLayoutManager manager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerViewBelle.setLayoutManager(gridLayoutManager);

        persenter = new Persenter();
        persenter.getGrilPicture(this, ApiInterf.class, count, false);

        recyclerViewBelle.addOnScrollListener(scrollListener);

        swipeRehreshBelle.setColorSchemeResources(R.color.orange_400);

        swipeRehreshBelle.setOnRefreshListener(refreshListener);
    }

    @OnClick(R.id.floatingActionButton)
    public void onViewClicked() {

        recyclerViewBelle.smoothScrollToPosition(0);
    }

    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refresh++;
            persenter.getGrilPictureRefresh(BelleFragment.this, ApiInterf.class, refresh * 2, false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRehreshBelle.setRefreshing(false);
                }
            }, 1000);
        }
    };

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();

                if (firstVisibleItemPosition == 0 || firstVisibleItemPosition == -1) {
                    floatingActionButton.setVisibility(View.INVISIBLE);
                } else {
                    floatingActionButton.setVisibility(View.VISIBLE);
                }

                if (gridLayoutManager.getItemCount() == 1) {
                    if (adapter != null) {
                        adapter.updateState(adapter.LOAD_NONE);
                        floatingActionButton.setVisibility(View.INVISIBLE);
                    }
                    return;
                }

                if (lastVisibleItemPosition + 1 == gridLayoutManager.getItemCount()) {
                    if (adapter != null) {
                        adapter.updateState(adapter.LOAD_TO_PULL);
                        adapter.updateState(adapter.LOAD_MORE);

                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            start++;
                            persenter.getGrilPicture(BelleFragment.this, ApiInterf.class, start * count, true);
                        }
                    }, 1000);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        }


    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getGrilPictureSuccess(GrilBean grilBean, boolean isLoadMore) {
        if (isLoadMore) {
            mGrilBean.getResults().addAll(grilBean.getResults());
//            adapter.notifyDataSetChanged();
        } else {
            mGrilBean = grilBean;
            adapter = new GrilRecyclerAdapter(mGrilBean.getResults());
            recyclerViewBelle.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getGrilPictureFaild() {
//        ShowUtils.toast(getActivity(), "网络错误...");
        llNetError.setVisibility(View.VISIBLE);
    }
}
