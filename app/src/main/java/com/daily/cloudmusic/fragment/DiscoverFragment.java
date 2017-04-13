package com.daily.cloudmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daily.cloudmusic.R;

/**
 * Created by daily on 2017/4/13.
 * 包括“个性推荐”，“歌单”，“主播电台”，“排行榜”
 * 主要实现Fragment内添加viewpager
 * 使用RecyclerView流
 */

public class DiscoverFragment extends Fragment {

    private ViewPager discoverViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_discover,container,false);
    }
}
