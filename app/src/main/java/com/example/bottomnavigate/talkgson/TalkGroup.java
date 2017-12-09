package com.example.bottomnavigate.talkgson;

import com.example.bottomnavigate.photogson.PhotoGroup;
import com.example.bottomnavigate.photogson.PhotoTitle;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/9.
 */

public class TalkGroup {

    @SerializedName("group")
    public Group group;

    public class Group {

        public String text;

        public User user;

        public class User {
            public String name;

            @SerializedName("avatar_url")
            public String userPicture;

        }

        @SerializedName("dislike_reason")
        public List<TalkTitle> talkTitles;

        @SerializedName("digg_count")
        public String digg;

        @SerializedName("bury_count")
        public String  bury;

        @SerializedName("comment_count")
        public String  comment;
    }
}
