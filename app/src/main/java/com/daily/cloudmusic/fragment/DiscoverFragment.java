package com.daily.cloudmusic.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daily.cloudmusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daily on 2017/4/13.
 * 包括“个性推荐”，“歌单”，“主播电台”，“排行榜”
 * 主要实现Fragment内添加viewpager
 * 使用RecyclerView流
 */

public class DiscoverFragment extends Fragment {

    private  View view;
    private ViewPager discoverViewPager;
    private FragmentPagerAdapter mAFragmentPagerAdapter;
    private List<Fragment> mDatas;

    /** 个性推荐 */
    private TextView txTopRecommend;

    /** 歌单 */
    private TextView txTopMusciList;

    /** 主播电台 */
    private TextView txTopRadioState;

    /** 排行榜 */
    private TextView txTopRankingList;

    /** 随动下标 */
    private ImageView imgTopLine;
    private int mScreen1_4;

    private int mCurrentPageIndex;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_main_discover,container,false);

        initTabline();
        findById();
        return view;
    }

    private void initTabline() {
        imgTopLine = (ImageView) view.findViewById(R.id.top_discover_line);
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_4 = outMetrics.widthPixels / 4;
        ViewGroup.LayoutParams lp = imgTopLine.getLayoutParams();
        lp.width = mScreen1_4;
        imgTopLine.setLayoutParams(lp);
    }

    /** 初始化 */
    private void findById() {

        discoverViewPager = (ViewPager) view.findViewById(R.id.discover_viewpager);
        txTopRecommend = (TextView) view.findViewById(R.id.tx_top_discover_recommend);
        txTopMusciList = (TextView) view.findViewById(R.id.tx_top_discover_list);
        txTopRadioState = (TextView) view.findViewById(R.id.tx_top_discover_radio_state);
        txTopRankingList = (TextView) view.findViewById(R.id.tx_top_discover_ranking_list);

        resetTextView();

        txTopRecommend.setOnClickListener(new MyOnClickListener(0));
        txTopMusciList.setOnClickListener(new MyOnClickListener(1));
        txTopRadioState.setOnClickListener(new MyOnClickListener(2));
        txTopRankingList.setOnClickListener(new MyOnClickListener(3));

        mDatas = new ArrayList<Fragment>();

        DiscoverRecommendFragment recommendFragment =new DiscoverRecommendFragment();
        DiscoverMusicListFragment musicListFragment =new DiscoverMusicListFragment();
        DiscoverRadioStateFragment radioStateFragment =new DiscoverRadioStateFragment();
        DiscoverRankingListFragment rankingListFragment =new DiscoverRankingListFragment();


        mDatas.add(recommendFragment);
        mDatas.add(musicListFragment);
        mDatas.add(radioStateFragment);
        mDatas.add(rankingListFragment);

        mAFragmentPagerAdapter =new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }

            @Override
            public int getCount() {
                return  mDatas.size();
            }
        };

        discoverViewPager.setAdapter(mAFragmentPagerAdapter);

        discoverViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        txTopRecommend.setTextColor(Color.RED);
                        break;
                    case 1:
                        txTopMusciList.setTextColor(Color.RED);
                        break;
                    case 2:
                        txTopRadioState.setTextColor(Color.RED);
                        break;
                    case 3:
                        txTopRankingList.setTextColor(Color.RED);
                        break;
                }

                mCurrentPageIndex = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPx) {
                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) imgTopLine
                        .getLayoutParams();

                if (mCurrentPageIndex == 0 && position == 0) {// 0->1
                    lp.leftMargin = (int) (positionOffset * mScreen1_4 + mCurrentPageIndex
                            * mScreen1_4);
                } else if (mCurrentPageIndex == 1 && position == 0) {// 1->0
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + (positionOffset - 1)
                            * mScreen1_4);
                } else if (mCurrentPageIndex == 1 && position == 1) {// 1->2
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + positionOffset
                            * mScreen1_4);
                } else if (mCurrentPageIndex == 2 && position == 1) {// 2->1
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + (positionOffset - 1)
                            * mScreen1_4);
                }else if (mCurrentPageIndex==2&&position==2){//2->3
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + positionOffset
                            * mScreen1_4);
                }else if (mCurrentPageIndex==3&&position==2){//3->2
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + (positionOffset - 1)
                            * mScreen1_4);
                }
                imgTopLine.setLayoutParams(lp);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void resetTextView() {
        // TODO Auto-generated method stub

        txTopRecommend.setTextColor(Color.GRAY);
        txTopMusciList.setTextColor(Color.GRAY);
        txTopRadioState.setTextColor(Color.GRAY);
        txTopRankingList.setTextColor(Color.GRAY);
    }


    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            discoverViewPager.setCurrentItem(index);
        }

    }
}
