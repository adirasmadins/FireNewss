package com.leyifu.firenewss.interf;

import com.leyifu.firenewss.bean.GrilBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @autoor hahaha
 * Created by hahaha on 2017/10/27 0027.
 */

public interface ApiInterf {

    @GET("data/福利/{count}/0")
    Observable<GrilBean> getGrilPicture(@Path("count") String count);

    @GET("data/福利/51/{count}")
    Observable<GrilBean> getGrilPictureRefresh(@Path("count") String count);

}
