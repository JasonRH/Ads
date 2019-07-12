package com.example.adslibrary;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.adslibrary.event.OnNextEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author RH
 * @date 2019/5/20
 */
public class AdsGallery extends FrameLayout {
    private final String TAG = "AdsGallery";

    private ViewPager2 viewPager2;

    private List<String> urls = new ArrayList<>();

    public static Handler handler = new Handler();


    public AdsGallery(Context context) {
        super(context);
    }

    public AdsGallery(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdsGallery(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init() {
        initView();
        //initAdapter();
        EventBus.getDefault().register(this);
    }


    private void initView() {
        /*
         * 用Java代码创建布局
         */
        viewPager2 = new ViewPager2(getContext());
        /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        viewPager2.setLayoutParams(params);*/
        this.addView(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //Log.e(TAG, "onPageScrolled: ");
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //Log.e(TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                //Log.e(TAG, "onPageScrollStateChanged: ");
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new OnNextEvent());
            }
        }, 5000);

    }

    private void initAdapter() {
        AdAdapter pagerAdapter = new AdAdapter(urls);
        viewPager2.setAdapter(pagerAdapter);
        //设置预加载数为0
        viewPager2.setOffscreenPageLimit(0);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    }

    public void setData(List<String> list) {
        urls.clear();
        urls.addAll(list);
        initAdapter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPageChange(OnNextEvent event) {
        int count = urls.size();
        int index = viewPager2.getCurrentItem();
        Log.i(TAG, "onPageChange:index " + index);
        index = (index + 1) % count;
        //设置无滚动效果
        viewPager2.setCurrentItem(index, false);
        if (urls.get(index).endsWith("mp4")){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onPageChange(new OnNextEvent());
                }
            }, 60000);
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onPageChange(new OnNextEvent());
                }
            }, 5000);
        }

    }


}
