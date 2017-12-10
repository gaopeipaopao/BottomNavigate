package com.example.bottomnavigate;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bottomnavigate.Adpatre.TalkAdapter;
import com.example.bottomnavigate.Adpatre.VidAdapter;
import com.example.bottomnavigate.RecycleItemDecoration.MyDecoration;
import com.example.bottomnavigate.talkgson.TalkDate;
import com.example.bottomnavigate.talkgson.TalkGroup;
import com.example.bottomnavigate.util.HttpUrl;
import com.example.bottomnavigate.util.Utility;
import com.example.bottomnavigate.vidgson.VidDate;
import com.example.bottomnavigate.vidgson.VidGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 泡泡 on 2017/11/28.
 */

public class Fragmentvid extends Fragment {

    public SwipeRefreshLayout swipeRefresh;
    private RecyclerView recycleView;
    private List<VidGroup> vidGroupList;
    private VidAdapter vidAdapter;
 //   private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.vid,container,false);
        recycleView= (RecyclerView) view.findViewById(R.id.vid_recycleview);
        vidGroupList=new ArrayList<>();
        vidAdapter=new VidAdapter(vidGroupList,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(vidAdapter);
        recycleView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swip_refresh_vid);
       // videoView=(VideoView)view.findViewById(R.id.vid_video);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefresh.setColorSchemeResources(R.color.colorPink);
//        if(!videoView.isPlaying()){
//            videoView.setVisibility(View.VISIBLE);
//            videoView.setBackgroundResource(R.drawable.zantingtingle);
//        }
        SharedPreferences editor= PreferenceManager.getDefaultSharedPreferences(getContext());
        String vidString=editor.getString("VidData",null);
        if(vidString==null){
            Log.d("bbbbbbbbb", "onActivityCreated: ");
            requestUrl();
        }else{
            Log.d("ccccccccc", "onActivityCreated: ");
            final VidDate vidDates= Utility.handleVidDate(vidString);
            for(VidGroup vidGroup:vidDates.Data.vidDataGroups){
                vidGroupList.add(vidGroup);
            }
            // photoGroupList=photoDate.Data.photoDataGroups;
            vidAdapter.notifyItemInserted(0);
        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                Log.d("rreeeeeee", "onRefresh: ");
                requestUrl();
            }
        });
    }

    private void requestUrl() {
        final String address="http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-104";
        HttpUrl.sendOkhttp(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Log.d("nnnnnnnnnnnn", "onResponse: "+responseText);
                final VidDate vidDate = Utility.handleVidDate(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (vidDate != null && "success".equals(vidDate.message)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("VidDate", responseText);
                            editor.apply();
                            for (VidGroup vidGroup : vidDate.Data.vidDataGroups) {
                                vidGroupList.add(0, vidGroup);
                                vidAdapter.notifyItemInserted(0);
                            }
                            vidAdapter.notifyDataSetChanged();
                            swipeRefresh.setRefreshing(false);
                            Log.d("ddddddddd", "run: ");

                        } else {
                            Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}