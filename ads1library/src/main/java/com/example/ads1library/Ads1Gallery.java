package com.example.ads1library;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ads1library.event.OnNextEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @author RH
 * @date 2019/5/21
 */
public class Ads1Gallery extends FrameLayout {
    private ViewPager viewPager;
    List<Fragment> fragmentList;
    public static Handler handler = new Handler();
    private AppCompatActivity activity;

    public Ads1Gallery(@NonNull Context context) {
        super(context);
    }

    public Ads1Gallery(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Ads1Gallery(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(AppCompatActivity activity) {
        this.activity = activity;
        View view = LayoutInflater.from(activity).inflate(R.layout.adgallery, this, true);
        viewPager = view.findViewById(R.id.viewPager);
        fragmentList = new ArrayList<>();
        //fragmentList.add(ImageFragment.newInstance());
        //fragmentList.add(new VideoFragement());


        //AdFragmentPagerAdapter pagerAdapter = new AdFragmentPagerAdapter(activity.getSupportFragmentManager(), fragmentList);
        //viewPager.setAdapter(pagerAdapter);
        initAdapter();
        viewPager.setOffscreenPageLimit(1);

        EventBus.getDefault().register(this);
    }

    private void initAdapter() {
        AdFragmentPagerAdapter pagerAdapter = new AdFragmentPagerAdapter(activity.getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);
    }

    public void setData(List<String> list) {
        fragmentList.clear();
        for (String s : list) {
            if (s.endsWith("mp4")) {
                fragmentList.add(VideoFragement.newInstance(s));
            } else {
                fragmentList.add(ImageFragment.newInstance(s));
            }

        }
        initAdapter();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPageChange(OnNextEvent event) {
        Log.e("Ads1Gallery", "onPageChange: ");
        int count = fragmentList.size();
        int index = viewPager.getCurrentItem();
        index = (index + 1) % count;
        //设置无滚动效果
        viewPager.setCurrentItem(index, false);
    }

}
