package com.example.bottomnavigate.vidgson;

import com.example.bottomnavigate.talkgson.TalkDate;
import com.example.bottomnavigate.talkgson.TalkGroup;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/9.
 */

public class VidDate {
    public String message;

    @SerializedName("data")
    public Data Data;

    public class Data{

        public String tip;

        @SerializedName("data")
        public List<VidGroup> vidDataGroups;
    }
}
