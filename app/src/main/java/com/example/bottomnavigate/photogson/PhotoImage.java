package com.example.bottomnavigate.photogson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/2.
 */

public class PhotoImage {

    public int width;
    public int r_height;
    public int r_width;
    public int height;

    @SerializedName("url_list")
    public List<PhotoUriList> photoUriLists;
}
