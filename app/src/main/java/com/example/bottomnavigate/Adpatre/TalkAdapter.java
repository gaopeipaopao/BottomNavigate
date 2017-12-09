package com.example.bottomnavigate.Adpatre;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bottomnavigate.R;
import com.example.bottomnavigate.photogson.PhotoGroup;
import com.example.bottomnavigate.talkgson.TalkGroup;
import com.example.bottomnavigate.talkgson.TalkTitle;

import java.util.List;

/**
 * Created by 泡泡 on 2017/12/9.
 */

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {

    private List<TalkGroup> talkGroupList;
    private Context context;

    public TalkAdapter(List<TalkGroup> talkGroupList, Context context) {
        this.context = context;
        this.talkGroupList = talkGroupList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View talkView;

        ImageView headerPicturetalk;
        TextView headerNametalk;
        TextView Contenttalk;
        Button digg_talk;
        TextView digg_count_talk;
        Button bury_talk;
        TextView bury_count_talk;
        Button  comment_talk;
        TextView comment_count_talk;

        public ViewHolder(View itemView) {
            super(itemView);
            talkView=itemView;
            headerPicturetalk = (ImageView) itemView.findViewById(R.id.talk_header_picture);
            headerNametalk = (TextView) itemView.findViewById(R.id.talk_header_name);
            Contenttalk = (TextView) itemView.findViewById(R.id.talk_content);
            digg_talk=(Button)itemView.findViewById(R.id.digg_talk);
            digg_count_talk=(TextView)itemView.findViewById(R.id.digg_count_talk);
            bury_talk=(Button) itemView.findViewById(R.id.bury_talk);
            bury_count_talk=(TextView)itemView.findViewById(R.id.bury_count_talk);
            comment_talk=(Button)itemView.findViewById(R.id.comment_talk);
            comment_count_talk=(TextView)itemView.findViewById(R.id.comment_count_talk);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.talk_recycle_adapter, parent, false);
        final TalkAdapter.ViewHolder holder = new TalkAdapter.ViewHolder(view);
        holder.digg_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                TalkGroup talkGroup = talkGroupList.get(position);
                int digg = Integer.parseInt(talkGroup.group.digg);
                digg=digg+1;
                String diggs=String.valueOf(digg);
                holder.digg_talk.setBackgroundResource(R.drawable.dianzan_yellow);
                holder.digg_count_talk.setText(diggs);
                holder.bury_talk.setBackgroundResource(R.drawable.buzan);
            }
        });
        holder.bury_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                TalkGroup talkGroup = talkGroupList.get(position);
                int digg = Integer.parseInt(talkGroup.group.bury);
                digg=digg+1;
                String diggs=String.valueOf(digg);
                holder.bury_talk.setBackgroundResource(R.drawable.buzan_yellow);
                holder.bury_count_talk.setText(diggs);
                holder.digg_talk.setBackgroundResource(R.drawable.dingding);
            }
        });
        holder.comment_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"hhh",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TalkGroup talkGroup = talkGroupList.get(position);
        holder.digg_count_talk.setText(talkGroup.group.digg);
        holder.bury_count_talk.setText(talkGroup.group.bury);
        holder.comment_count_talk.setText(talkGroup.group.comment);
        holder.headerNametalk.setText(talkGroup.group.user.name);
        List<TalkTitle> talkTitles=talkGroup.group.talkTitles;
        String content = null;
        for(TalkTitle talkTitle:talkTitles){
            if(talkTitle.title.charAt(0)=='吧'){
                content="#"+talkTitle.title.charAt(2);
                for(int i=3;i<talkTitle.title.length();i++){
                    content=content+talkTitle.title.charAt(i);
                }
                content=content+'#'+' ';
                break;
            }
        }
        holder.Contenttalk.setText(content+talkGroup.group.text);
        holder.digg_talk.setBackgroundResource(R.drawable.dingding);
        holder.bury_talk.setBackgroundResource(R.drawable.buzan);
        Glide.with(context).load(talkGroup.group.user.userPicture).into(holder.headerPicturetalk);

    }

    @Override
    public int getItemCount() {
        return talkGroupList.size();
    }

    public void add(TalkGroup group, int position) {
        talkGroupList.add(position, group);
        notifyItemInserted(position);
    }
}
