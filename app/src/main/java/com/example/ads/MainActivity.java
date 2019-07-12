package com.example.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ads1library.Ads1Gallery;
import com.example.adslibrary.AdsGallery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RH
 */
public class MainActivity extends AppCompatActivity {
    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGallery1();
    }

    private void initGallery() {
        final AdsGallery adsGallery = findViewById(R.id.ads);
        adsGallery.init();

        urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514171055360-p1.jpg");
        urls.add("https://static.hgobox.com/worker/1/video/2019/05/14/20190514165817660-test2.mp4");
        urls.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514171050980-p2.jpg");
        adsGallery.setData(urls);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urls.clear();
                urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514175006537-p3.jpg");
                urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514175006537-p3.jpg");
                urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514175006537-p3.jpg");
                urls.add("https://static.hgobox.com/worker/1/video/2019/05/15/20190515174755265-test3.mp4");
                adsGallery.setData(urls);
            }
        });
    }

    private void initGallery1() {
        final Ads1Gallery ads1Gallery = findViewById(R.id.ads1);
        ads1Gallery.initView(this);
        urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514171055360-p1.jpg");
        //urls.add("https://static.hgobox.com/worker/1/video/2019/05/14/20190514165817660-test2.mp4");
        urls.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        //urls.add("/sdcard/hgobox/pf/A.mp4");
        urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514171050980-p2.jpg");
        ads1Gallery.setData(urls);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urls.clear();
                urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514175006537-p3.jpg");
                urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514171050980-p2.jpg");
                urls.add("https://static.hgobox.com/worker/1/image/2019/05/14/20190514171055360-p1.jpg");
                //urls.add("https://static.hgobox.com/worker/1/video/2019/05/15/20190515174755265-test3.mp4");
                ads1Gallery.setData(urls);
            }
        });
    }

}
