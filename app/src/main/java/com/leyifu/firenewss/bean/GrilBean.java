package com.leyifu.firenewss.bean;

import java.util.List;

/**
 * @autoor hahaha
 * Created by hahaha on 2017/10/27 0027.
 */

public class GrilBean {


    /**
     * error : false
     * results : [{"_id":"59f0054a421aa90fe2f02bf4","createdAt":"2017-10-25T11:30:18.697Z","desc":"2017-10-25","publishedAt":"2017-10-25T11:39:10.950Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171025112955_lmesMu_katyteiko_25_10_2017_11_29_43_270.jpeg","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<GrilResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GrilResultsBean> getResults() {
        return results;
    }

    public void setResults(List<GrilResultsBean> results) {
        this.results = results;
    }

}
