package com.example.bottomnavigate.vidgson;

import com.example.bottomnavigate.photogson.PhotoGroup;
import com.example.bottomnavigate.photogson.PhotoImage;
import com.example.bottomnavigate.photogson.PhotoTitle;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/9.
 */

public class VidGroup {

    @SerializedName("group")
    public Group group;

    public class Group {

        @SerializedName("720p_video")
        public Video video;
        public class Video{
            @SerializedName("width")
            public int width;

            @SerializedName("url_list")
            public List<VidUrlList>  vidUrlLists;

            @SerializedName("height")
            public int height;
        }

        @SerializedName("user")
        public User user;

        public class User{
            public String name;

            @SerializedName("avatar_url")
            public String userPicture;

        }

        @SerializedName("video_height")
        public String   video_height;

        public String text;

        @SerializedName("dislike_reason")
        public List<VidTitle> vidTitles;


        @SerializedName("medium_cover")
        public CoverPhoto coverPhoto;
        public class CoverPhoto{
            @SerializedName("url_list")
            public List<VidUrlList>  vidUrlListList;
        }

        @SerializedName("digg_count")
        public String digg;

        @SerializedName("bury_count")
        public String  bury;

        @SerializedName("comment_count")
        public String  comment;

        @SerializedName("category_name")
        public  String  category;


        @SerializedName("video_width")
        public String  video_width;
    }

}
