package com.example.bottomnavigate.talkgson;

import com.example.bottomnavigate.photogson.PhotoDate;
import com.example.bottomnavigate.photogson.PhotoGroup;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/9.
 */

public class TalkDate {

    public String message;

    @SerializedName("data")
    public Data Data;

    public class Data{

        public String tip;

        @SerializedName("data")
        public List<TalkGroup> talkDataGroups;
    }
}
