package com.example.bottomnavigate.Adpatre;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigate.R;
import com.example.bottomnavigate.talkgson.TalkGroup;
import com.example.bottomnavigate.talkgson.TalkTitle;
import com.example.bottomnavigate.vidgson.VidGroup;

import java.util.List;

import static android.provider.MediaStore.Images.Thumbnails.MINI_KIND;

/**
 * Created by 泡泡 on 2017/12/9.
 */

public class VidAdapter extends RecyclerView.Adapter<VidAdapter.ViewHolder> {

    private List<VidGroup> vidGroupList;
    private Context context;
    private GestureDetector mGesture;
    FrameLayout.LayoutParams para;

    public VidAdapter(List<VidGroup> vidGroupList, Context context) {
        this.context = context;
        this.vidGroupList = vidGroupList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View vidView;

        ImageView headerPicturevid;
        TextView headerNamevid;
        TextView Contentvid;
        Button digg_vid;
        TextView digg_count_vid;
        Button bury_vid;
        TextView bury_count_vid;
        Button  comment_vid;
        TextView comment_count_vid;
        VideoView vid_video;
        ImageView iv;
        Button click;
        SwipeRefreshLayout swipeRefresh;

        public ViewHolder(View itemView) {
            super(itemView);
            vidView=itemView;
            headerPicturevid = (ImageView) itemView.findViewById(R.id.vid_header_picture);
            headerNamevid = (TextView) itemView.findViewById(R.id.vid_header_name);
            Contentvid = (TextView) itemView.findViewById(R.id.vid_content);
            digg_vid=(Button)itemView.findViewById(R.id.digg_vid);
            digg_count_vid=(TextView)itemView.findViewById(R.id.digg_count_vid);
            bury_vid=(Button) itemView.findViewById(R.id.bury_vid);
            bury_count_vid=(TextView)itemView.findViewById(R.id.bury_count_vid);
            comment_vid=(Button)itemView.findViewById(R.id.comment_vid);
            comment_count_vid=(TextView)itemView.findViewById(R.id.comment_count_vid);
            vid_video=(VideoView) itemView.findViewById(R.id.vid_video);
            iv=(ImageView)itemView.findViewById(R.id.iv);
            click=(Button)itemView.findViewById(R.id.click_vid);
            swipeRefresh=(SwipeRefreshLayout)itemView.findViewById(R.id.swip_refresh_vid);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vid_recycle_adapter, parent, false);
        final VidAdapter.ViewHolder holder = new VidAdapter.ViewHolder(view);
        holder.digg_vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                VidGroup vidGroup = vidGroupList.get(position);
                int digg = Integer.parseInt(vidGroup.group.digg);
                digg=digg+1;
                String diggs=String.valueOf(digg);
                holder.digg_vid.setBackgroundResource(R.drawable.dianzan_yellow);
                holder.digg_count_vid.setText(diggs);
                holder.bury_vid.setBackgroundResource(R.drawable.buzan);
            }
        });
        holder.bury_vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                VidGroup vidGroup = vidGroupList.get(position);
                int digg = Integer.parseInt(vidGroup.group.bury);
                digg=digg+1;
                String diggs=String.valueOf(digg);
                holder.bury_vid.setBackgroundResource(R.drawable.buzan_yellow);
                holder.bury_count_vid.setText(diggs);
                holder.digg_vid.setBackgroundResource(R.drawable.dingding);
            }
        });
        holder.comment_vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"hhh",Toast.LENGTH_SHORT).show();
            }
        });
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                VidGroup vidGroup = vidGroupList.get(position);
                holder.iv.setVisibility(View.GONE);
                holder.click.setVisibility(View.GONE);
                holder.vid_video.setVisibility(View.VISIBLE);
                //holder.vid_video.setVideoURI(Uri.parse(vidGroup.group.video.vidUrlLists.get(0).url));
                Log.d("bhbhbhbhbhbh", "onClick: "+holder.vid_video);
                //Glide.with(context).load(vidGroup.group.coverPhoto.vidUrlListList.get(0).url);
            }
        });
//        holder.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                int position = holder.getAdapterPosition();
//                VidGroup vidGroup = vidGroupList.get(position);
//                holder.vid_video.pause();
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VidGroup vidGroup = vidGroupList.get(position);
        holder.digg_count_vid.setText(vidGroup.group.digg);
        holder.bury_count_vid.setText(vidGroup.group.bury);
        holder.comment_count_vid.setText(vidGroup.group.comment);
        holder.headerNamevid.setText(vidGroup.group.user.name);
        String content="#"+vidGroup.group.category+'#'+' '+vidGroup.group.text;
        holder.Contentvid.setText(content);
        holder.digg_vid.setBackgroundResource(R.drawable.dingding);
        holder.bury_vid.setBackgroundResource(R.drawable.buzan);
        Glide.with(context).load(vidGroup.group.user.userPicture).into(holder.headerPicturevid);
        Glide.with(context).load(vidGroup.group.coverPhoto.vidUrlListList.get(0).url).into(holder.iv);
        holder.vid_video.setMediaController(new MediaController(context));
        holder.vid_video.setVideoURI(Uri.parse(vidGroup.group.video.vidUrlLists.get(0).url));
        holder.vid_video.start();
    }

    @Override
    public int getItemCount() {
        return vidGroupList.size();
    }

    public void add(VidGroup group, int position) {
        vidGroupList.add(position, group);
        notifyItemInserted(position);
    }
}
