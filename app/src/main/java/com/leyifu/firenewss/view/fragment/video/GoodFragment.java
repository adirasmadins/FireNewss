package com.leyifu.firenewss.view.fragment.video;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leyifu.firenewss.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoodFragment extends Fragment {


    private static GoodFragment goodFragment;

    private GoodFragment() {
        // Required empty public constructor
    }

    public static GoodFragment getInstance() {
        if (goodFragment == null) {
            synchronized (GoodFragment.class) {
                if (goodFragment == null) {
                    goodFragment = new GoodFragment();
                }
            }
        }
        return goodFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_good, container, false);
    }

}
