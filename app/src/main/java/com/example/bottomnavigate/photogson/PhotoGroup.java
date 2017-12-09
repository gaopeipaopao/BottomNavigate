package com.example.bottomnavigate.photogson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/2.
 */

public class PhotoGroup {

    @SerializedName("group")
    public Group group;

    public class Group {
        public User user;

        public class User{
            public String name;

            @SerializedName("avatar_url")
            public String userPicture;

        }

        public String text;

        @SerializedName("dialike_reason")
        public List<PhotoTitle> photoTitles;

        @SerializedName("large_image")
        public PhotoImage photoImage;

        @SerializedName("digg_count")
        public String digg;

        @SerializedName("bury_count")
        public String  bury;

        @SerializedName("comment_count")
        public String  comment;
    }

}
