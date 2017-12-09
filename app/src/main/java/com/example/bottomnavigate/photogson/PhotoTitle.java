package com.example.bottomnavigate.photogson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 泡泡 on 2017/12/2.
 */

public class PhotoTitle {
    public int type;

    @SerializedName("id")
    public int user_id;

    public String title;
}
