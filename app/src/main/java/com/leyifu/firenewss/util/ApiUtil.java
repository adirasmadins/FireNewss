package com.leyifu.firenewss.util;


import com.google.gson.GsonBuilder;
import com.leyifu.firenewss.constant.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @autoor hahaha
 * Created by hahaha on 2017/10/27 0027.
 */

public class ApiUtil {


    public static Retrofit getRetrofit() {


        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
