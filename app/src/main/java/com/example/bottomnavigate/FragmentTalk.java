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

import com.example.bottomnavigate.Adpatre.PhotoAdapter;
import com.example.bottomnavigate.Adpatre.TalkAdapter;
import com.example.bottomnavigate.RecycleItemDecoration.MyDecoration;
import com.example.bottomnavigate.photogson.PhotoDate;
import com.example.bottomnavigate.photogson.PhotoGroup;
import com.example.bottomnavigate.talkgson.TalkDate;
import com.example.bottomnavigate.talkgson.TalkGroup;
import com.example.bottomnavigate.util.HttpUrl;
import com.example.bottomnavigate.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 泡泡 on 2017/11/28.
 */

public class FragmentTalk extends Fragment {

    public SwipeRefreshLayout swipeRefresh;
    private RecyclerView recycleView;
    private List<TalkGroup> talkGroupList;
    private TalkAdapter talkAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.talk,container,false);
        recycleView= (RecyclerView) view.findViewById(R.id.talk_recycleview);
        talkGroupList=new ArrayList<>();
        talkAdapter=new TalkAdapter(talkGroupList,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(talkAdapter);
        recycleView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swip_refresh_talk);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefresh.setColorSchemeResources(R.color.colorPink);
        SharedPreferences editor= PreferenceManager.getDefaultSharedPreferences(getContext());
        String talkString=editor.getString("TalkData",null);
        if(talkString==null){
            Log.d("bbbbbbbbb", "onActivityCreated: ");
            requestUrl();
        }else{
            Log.d("ccccccccc", "onActivityCreated: ");
            final TalkDate talkDates= Utility.handleTalkDate(talkString);
            for(TalkGroup talkGroup:talkDates.Data.talkDataGroups){
                talkGroupList.add(talkGroup);
            }
            // photoGroupList=photoDate.Data.photoDataGroups;
            talkAdapter.notifyItemInserted(0);
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
        final String address="http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-102";
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
                final TalkDate talkDate = Utility.handleTalkDate(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (talkDate != null && "success".equals(talkDate.message)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("TalkDate", responseText);
                            editor.apply();
                            for (TalkGroup talkGroup : talkDate.Data.talkDataGroups) {
                                talkGroupList.add(0, talkGroup);
                                talkAdapter.notifyItemInserted(0);
                            }
                            talkAdapter.notifyDataSetChanged();
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
