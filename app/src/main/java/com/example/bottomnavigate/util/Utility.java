package com.example.bottomnavigate.util;

import android.util.Log;

import com.example.bottomnavigate.photogson.PhotoDate;
import com.example.bottomnavigate.talkgson.TalkDate;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 泡泡 on 2017/12/2.
 */

public class Utility {

    public static PhotoDate handlePhotoDate(String response) {
        try {
            //Log.d("cccccccccccc", "handlePhotoDate: "+response);
            // JSONObject jsonObject = new JSONObject(response);
            //// Log.d("ddddddddd", "handlePhotoDate: "+jsonObject);
            // JSONArray jsonArray = jsonObject.getJSONArray("data");
            //  String  photoContent=jsonArray.getJSONObject(0).toString();
            //  String photoDateContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(response, PhotoDate.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static TalkDate handleTalkDate(String response) {
        try {
            return new Gson().fromJson(response, TalkDate.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
