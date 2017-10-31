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
public class HotFragment extends Fragment {


    private static HotFragment hotFragment;

    private HotFragment() {
        // Required empty public constructor
    }

    public static HotFragment getInstance(){
        if (hotFragment == null) {
            synchronized (HotFragment.class) {
                if (hotFragment == null) {
                    hotFragment = new HotFragment();
                }
            }
        }
        return hotFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot, container, false);
    }

}
