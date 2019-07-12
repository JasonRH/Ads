package com.example.ads1library;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.ads1library.event.OnNextEvent;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author RH
 * @date 2019/5/21
 */
public class VideoFragement extends Fragment {
    private final String TAG = "VideoFragement";
    private VideoView videoView;
    private String url;

    public static Fragment newInstance(String url) {
        VideoFragement videoFragement = new VideoFragement();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        videoFragement.setArguments(bundle);
        return videoFragement;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        videoView = view.findViewById(R.id.ad_video);
        if (getArguments() != null) {
            url = getArguments().getString("url");
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
        if (url != null && !TextUtils.isEmpty(url)) {
            videoView.setVideoPath(url);
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                //mp.start();
                //mp.setLooping(true);
                Log.e(TAG, "视频加载完成: ");
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                EventBus.getDefault().post(new OnNextEvent());
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint: " + isVisibleToUser);
        if (isVisibleToUser) {
            //播放广告
            videoView.start();
        } else {
            //暂停广告
            if (videoView != null) {
                videoView.pause();
                videoView.resume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }
}
