package com.leyifu.firenewss.persenter;

import com.leyifu.firenewss.bean.GrilBean;
import com.leyifu.firenewss.interf.ApiInterf;
import com.leyifu.firenewss.interf.IgetGrilPicture;
import com.leyifu.firenewss.util.ApiUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @autoor hahaha
 * Created by hahaha on 2017/10/27 0027.
 */

public class Persenter {


    /**
     * 美女图片
     *
     * @param igetGrilPicture
     * @param apiInterfClass
     * @param count
     * @param isLoadMore
     */
    public void getGrilPicture(final IgetGrilPicture igetGrilPicture, Class<ApiInterf> apiInterfClass, int count, final boolean isLoadMore) {

        Observable<GrilBean> observable = ApiUtil.getRetrofit().create(apiInterfClass).getGrilPicture((count + 1) + "");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GrilBean>() {
                    @Override
                    public void call(GrilBean grilBean) {
                        igetGrilPicture.getGrilPictureSuccess(grilBean, isLoadMore);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        igetGrilPicture.getGrilPictureFaild();
                    }
                });
    }


    /**
     * 美女图片
     *
     * @param igetGrilPicture
     * @param apiInterfClass
     * @param count
     * @param isLoadMore
     */
    public void getGrilPictureRefresh(final IgetGrilPicture igetGrilPicture, Class<ApiInterf> apiInterfClass, int count, final boolean isLoadMore) {

        Observable<GrilBean> observable = ApiUtil.getRetrofit().create(apiInterfClass).getGrilPictureRefresh(count+"");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GrilBean>() {
                    @Override
                    public void call(GrilBean grilBean) {
                        igetGrilPicture.getGrilPictureSuccess(grilBean, isLoadMore);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        igetGrilPicture.getGrilPictureFaild();
                    }
                });
    }
}
