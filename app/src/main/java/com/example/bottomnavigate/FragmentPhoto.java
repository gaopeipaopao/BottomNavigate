package com.example.bottomnavigate;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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
import com.example.bottomnavigate.RecycleItemDecoration.MyDecoration;
import com.example.bottomnavigate.photogson.PhotoDate;
import com.example.bottomnavigate.photogson.PhotoGroup;
import com.example.bottomnavigate.util.HttpUrl;
import com.example.bottomnavigate.util.Utility;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 泡泡 on 2017/11/28.
 */

public class FragmentPhoto extends Fragment {

    public SwipeRefreshLayout swipeRefresh;
    private RecyclerView recycleView;
    private List<PhotoGroup>  photoGroupList;
    private PhotoAdapter photoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.photo,container,false);
        recycleView= (RecyclerView) view.findViewById(R.id.photo_recycleview);
        photoGroupList=new ArrayList<>();
        photoAdapter=new PhotoAdapter(photoGroupList,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(photoAdapter);
        recycleView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swip_refresh_photo);
        Log.d("aaaaaaa", "onCreateView: ");
//        photoAdapter=new PhotoAdapter(photoGroupList,getContext());
//        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//        recycleView.setLayoutManager(layoutManager);
//        recycleView.setAdapter(photoAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefresh.setColorSchemeResources(R.color.colorPink);
        SharedPreferences editor=PreferenceManager.getDefaultSharedPreferences(getContext());
        String photoString=editor.getString("PhotoData",null);
        if(photoString==null){
            Log.d("bbbbbbbbb", "onActivityCreated: ");
            requestUrl();
        }else{
            Log.d("ccccccccc", "onActivityCreated: ");
            final PhotoDate photoDates=Utility.handlePhotoDate(photoString);
            for(PhotoGroup photoGroup:photoDates.Data.photoDataGroups){
                photoGroupList.add(photoGroup);
            }
            // photoGroupList=photoDate.Data.photoDataGroups;
            photoAdapter.notifyItemInserted(0);
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
        final String address="http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-103";
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
                Log.d("hhhhhhhhh", "run: "+responseText);
                final PhotoDate photoDate = Utility.handlePhotoDate(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (photoDate != null && "success".equals(photoDate.message)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                            editor.putString("PhotoDate", responseText);
                            editor.apply();
                            for (PhotoGroup photoGroup : photoDate.Data.photoDataGroups) {
                                photoGroupList.add(0, photoGroup);
                                photoAdapter.notifyItemInserted(0);
                            }
                            photoAdapter.notifyDataSetChanged();
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
