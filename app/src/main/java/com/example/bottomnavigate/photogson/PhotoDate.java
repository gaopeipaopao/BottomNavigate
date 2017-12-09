package com.example.bottomnavigate.photogson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/2.
 */

public class PhotoDate {

    public String message;

    @SerializedName("data")
    public Data Data;

    public class Data{

        public String tip;

        @SerializedName("data")
        public List<PhotoGroup> photoDataGroups;
    }
}
