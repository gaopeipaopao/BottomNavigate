package com.example.bottomnavigate;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

/**
 * Created by 泡泡 on 2017/11/28.
 */

public class FragmentRecommend extends Fragment {

    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recommend,container,false);
        /*videoView=(VideoView)view.findViewById(R.id.video_view);
        String url="http://ic.snssdk.com/neihan/video/playback/1489235732.1/?video_id=d2a0fc9bb4784a6c983cd4a341df6d76&quality=360p&line=0&is_gif=0&device_platform=android";
        Uri uri=Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.start();*/
        return view;
    }


}
