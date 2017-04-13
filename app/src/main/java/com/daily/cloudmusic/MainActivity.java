package com.daily.cloudmusic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.daily.cloudmusic.fragment.DiscoverFragment;
import com.daily.cloudmusic.fragment.FriendsFragment;
import com.daily.cloudmusic.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener{

    private ImageButton atcionBarMusicImgBtn;
    private ImageButton atcionBarDiscoverImgBtn;
    private ImageButton atcionBarFriendsImgBtn;
    private ImageButton atcionBarSearchImgBtn;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.actionbar_menu);
        toolbar.setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    private void initView() {

         mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
         atcionBarMusicImgBtn = (ImageButton) findViewById(R.id.actionbar_img_btn_music);
         atcionBarDiscoverImgBtn = (ImageButton) findViewById(R.id.actionbar_img_btn_discover);
         atcionBarFriendsImgBtn= (ImageButton) findViewById(R.id.actionbar_img_btn_friends);
         atcionBarSearchImgBtn= (ImageButton) findViewById(R.id.actionbar_img_btn_search);

        atcionBarMusicImgBtn.setOnClickListener(new MyOnClickListener(0));
        atcionBarDiscoverImgBtn.setOnClickListener(new MyOnClickListener(1));
        atcionBarFriendsImgBtn.setOnClickListener(new MyOnClickListener(2));


        atcionBarMusicImgBtn.setBackgroundResource(R.drawable.actionbar_music_selected);
        mDatas =new ArrayList<Fragment>();

        MusicFragment musicFragment =new MusicFragment();
        DiscoverFragment discoverFragment =new DiscoverFragment();
        FriendsFragment friendsFragment =new FriendsFragment();

        mDatas.add(musicFragment);
        mDatas.add(discoverFragment);
        mDatas.add(friendsFragment);

        mFragmentPagerAdapter =new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }
        };

        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setOnPageChangeListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                selectFragmentState(0);
                break;
            case 1:
                selectFragmentState(1);
                break;
            case 2:
                selectFragmentState(2);
            break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public  class MyOnClickListener implements View.OnClickListener{

        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View view) {
            mViewPager.setCurrentItem(index);
        }
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.actionbar_img_btn_music:
//                selectFragmentState(0);
//                onPageSelected(0);
//                break;
//            case R.id.actionbar_img_btn_discover:
//                selectFragmentState(1);
//                onPageSelected(1);
//                break;
//            case R.id.actionbar_img_btn_friends:
//                selectFragmentState(2);
//                onPageSelected(2);
//                break;
//        }
//    }

     private  void  selectFragmentState(int state){
        switch (state){
            case 0:
                atcionBarMusicImgBtn.setBackgroundResource(R.drawable.actionbar_music_selected);
                atcionBarDiscoverImgBtn .setBackgroundResource(R.drawable.actionbar_discover_normal);
                atcionBarFriendsImgBtn.setBackgroundResource(R.drawable.actionbar_friends_normal);
                break;

            case  1:
                atcionBarMusicImgBtn.setBackgroundResource(R.drawable.actionbar_music_normal);
                atcionBarDiscoverImgBtn .setBackgroundResource(R.drawable.actionbar_discover_selected);
                atcionBarFriendsImgBtn.setBackgroundResource(R.drawable.actionbar_friends_normal);
                break;

            case  2:
                atcionBarMusicImgBtn.setBackgroundResource(R.drawable.actionbar_music_normal);
                atcionBarDiscoverImgBtn .setBackgroundResource(R.drawable.actionbar_discover_normal);
                atcionBarFriendsImgBtn.setBackgroundResource(R.drawable.actionbar_friends_selected);
                break;

        }
    }

}
