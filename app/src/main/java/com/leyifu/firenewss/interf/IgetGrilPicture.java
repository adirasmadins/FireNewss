package com.leyifu.firenewss.interf;

import com.leyifu.firenewss.bean.GrilBean;

/**
 * @autoor hahaha
 * Created by hahaha on 2017/10/27 0027.
 */

public interface IgetGrilPicture {

    void getGrilPictureSuccess(GrilBean grilBean, boolean isLoadMore);

    void getGrilPictureFaild();
}
