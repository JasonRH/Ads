package com.example.ads1library;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.ads1library.event.OnNextEvent;
import com.example.ads1library.image.MyGlideOptions;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

/**
 * @author RH
 * @date 2019/5/21
 */
public class ImageFragment extends Fragment {


    public static Fragment newInstance(String url) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        AppCompatImageView imageView = view.findViewById(R.id.ad_image);
        if (getArguments() != null) {
            Glide.with(getContext()).load(getArguments().getString("url")).apply(MyGlideOptions.OPTIONS).into(imageView);
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Ads1Gallery.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new OnNextEvent());
                }
            }, 5000);
        }
    }
}
