package com.example.value.instantresume;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

public class MainActivity extends FragmentActivity {
    static final int PAGES = 2;
    MyAdapter mAdapter;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        showOverflowMenu(this);
    }

    // this method force showing menu button in device with hardware menu button
    private void showOverflowMenu(FragmentActivity activity) {
        try {
            ViewConfiguration config = ViewConfiguration.get(activity);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return MainFragment.init(position);
                default:
                    return SetupFragment.init(position);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Resources res = getResources();
        int INTEGER_MAIN_PAGE_NUM = res.getInteger(R.integer.integer_main_page_num);
        int INTEGER_SETUP_PAGE_NUM = res.getInteger(R.integer.integer_setup_page_num);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        if (preferences.contains("url")) {
            setCurrentPagerItem(INTEGER_MAIN_PAGE_NUM);
        } else {
            setCurrentPagerItem(INTEGER_SETUP_PAGE_NUM);
        }
    }

    public void setCurrentPagerItem(int page_num) {
        mPager.setCurrentItem(page_num);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mPager.addOnPageChangeListener(listener);
    }
}