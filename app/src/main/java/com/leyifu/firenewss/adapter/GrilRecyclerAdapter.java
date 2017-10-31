package com.leyifu.firenewss.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leyifu.firenewss.R;
import com.leyifu.firenewss.bean.GrilResultsBean;
import com.leyifu.firenewss.view.activity.GirlPictureActivity;

import java.util.List;

/**
 * @autoor hahaha
 * Created by hahaha on 2017/10/27 0027.
 */

public class GrilRecyclerAdapter extends RecyclerView.Adapter<GrilRecyclerAdapter.ViewHolder> {

    private List<GrilResultsBean> mGrilBean;

    private final int FOOTER_TYPE = -1;
    public final int LOAD_NONE = 0;
    public final int LOAD_TO_PULL = 1;
    public final int LOAD_MORE = 2;
    private Context context;
    private int state;

    public GrilRecyclerAdapter(List<GrilResultsBean> grilBean) {
        this.mGrilBean = grilBean;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return FOOTER_TYPE;
        } else {
            return position;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        context = parent.getContext();

        if (viewType == FOOTER_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.footer_load_more, null);
            return new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_gril_recycler, null);
            final ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.ll_picture.setOnClickListener(new View.OnClickListener() {
                private Intent intent;

                @Override
                public void onClick(View view) {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    intent = new Intent(context, GirlPictureActivity.class);
                    intent.putExtra("pic_url", mGrilBean.get(adapterPosition).getUrl());
                    context.startActivity(intent);
                }
            });
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        } else if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = holder;
            viewHolder.bindItem(mGrilBean, position);
        }
    }

    @Override
    public int getItemCount() {
        return mGrilBean == null ? 0 : mGrilBean.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_item_gril;
        private final LinearLayout ll_picture;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_item_gril = (ImageView) itemView.findViewById(R.id.iv_item_gril);
            ll_picture = ((LinearLayout) itemView.findViewById(R.id.ll_picture));
        }

        public void bindItem(List<GrilResultsBean> mGrilBean, int position) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int widthPixels = displayMetrics.widthPixels;
            int heightPixels = displayMetrics.heightPixels;
            ViewGroup.LayoutParams layoutParams = iv_item_gril.getLayoutParams();
            layoutParams.width = widthPixels / 2;
            layoutParams.height = heightPixels / 3;
            Glide.with(context).load(mGrilBean.get(position).getUrl()).into(iv_item_gril);
        }
    }

    private class FooterViewHolder extends ViewHolder {

        private final ProgressBar footer_progress_bar;
        private final TextView tv_footer_text;
        private final LinearLayout ll_footer;
        private View view;

        public FooterViewHolder(View view) {
            super(view);
            this.view = view;
            ll_footer = (LinearLayout) view.findViewById(R.id.ll_footer);
            footer_progress_bar = ((ProgressBar) view.findViewById(R.id.footer_progress_bar));
            tv_footer_text = ((TextView) view.findViewById(R.id.tv_footer_text));
            LinearLayoutCompat.LayoutParams linearLayoutCompat = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            itemView.setLayoutParams(linearLayoutCompat);
            ll_footer.setGravity(Gravity.CENTER_HORIZONTAL);

        }

        public void bindItem() {
            switch (state) {
                case LOAD_TO_PULL:
                    footer_progress_bar.setVisibility(View.GONE);
                    tv_footer_text.setText(R.string.load_to_pull);
                    view.setVisibility(View.VISIBLE);
                    break;

                case LOAD_MORE:
                    footer_progress_bar.setVisibility(View.VISIBLE);
                    tv_footer_text.setText(R.string.load_more);
                    view.setVisibility(View.VISIBLE);
                    break;

                case LOAD_NONE:
                    footer_progress_bar.setVisibility(View.GONE);
                    tv_footer_text.setText(R.string.load_none);
                    break;
                default:
                    break;

            }
        }
    }

    public void updateState(int state) {
        this.state = state;
        notifyDataSetChanged();
    }
}
