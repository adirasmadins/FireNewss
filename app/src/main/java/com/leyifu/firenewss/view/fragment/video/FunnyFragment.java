package com.leyifu.firenewss.view.fragment.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leyifu.firenewss.R;


public class FunnyFragment extends Fragment {

    private static FunnyFragment funnyFragment;

    private FunnyFragment(){

    }

    public static FunnyFragment getInstance(){
        if (funnyFragment == null) {
            synchronized (FunnyFragment.class) {
                if (funnyFragment == null) {
                    funnyFragment = new FunnyFragment();
                }
            }
        }
        return funnyFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_funny, container, false);
    }


}
