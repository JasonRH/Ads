package com.example.adslibrary;

import android.content.Context;

import androidx.appcompat.widget.AppCompatImageView;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.adslibrary.event.OnNextEvent;
import com.example.adslibrary.image.MyGlideOptions;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


/**
 * @author RH
 * @date 2019/5/20
 */
public class AdAdapter extends RecyclerView.Adapter<AdAdapter.ViewHolder> {

    private List<String> urls;
    private Context mContext;

    public AdAdapter(List<String> urls) {
        this.urls = urls;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imageView;
        private VideoView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            videoView = itemView.findViewById(R.id.video);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_adapter_ad, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = urls.get(position);
        if (url.endsWith("mp4")) {
            //视频
            holder.imageView.setVisibility(View.GONE);
            holder.videoView.setVisibility(View.VISIBLE);
            Log.e("AdAdapter", "加载视频" + url);
            holder.videoView.setVideoPath("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
            holder.videoView.start();
            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    mp.setLooping(true);
                }
            });
            holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                }
            });
            holder.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    return false;
                }
            });

        } else {
            //图片
            holder.videoView.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(url).apply(MyGlideOptions.OPTIONS).into(holder.imageView);
            Log.e("AdAdapter", "加载图片" + url);
        }

    }


    @Override
    public int getItemCount() {
        return urls.size();
    }
}
