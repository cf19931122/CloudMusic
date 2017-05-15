package com.daily.cloudmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daily.cloudmusic.R;

/**
 * Created by daily on 2017/5/15.
 */

public class DiscoverMusicListFragment extends Fragment {

    private  View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_discover_musiclist,container,false);

        return view;
    }
}
