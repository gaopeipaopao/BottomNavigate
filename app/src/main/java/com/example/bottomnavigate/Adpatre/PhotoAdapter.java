package com.example.bottomnavigate.Adpatre;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bottomnavigate.R;
import com.example.bottomnavigate.photogson.PhotoGroup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static android.R.id.content;

/**
 * Created by 泡泡 on 2017/12/5.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<PhotoGroup> photoGroupList;
    private Context context;
    LinearLayout.LayoutParams para;
    WebSettings settings ;
    Bitmap bm;
    WindowManager wm;
    private int height = 0;
    private int width = 0;
    private SpannableString spannableString;

    public PhotoAdapter(List<PhotoGroup> photoGroupList, Context context) {
        this.context = context;
        this.photoGroupList = photoGroupList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View photoView;

        ImageView headerPicture;
        TextView headerName;
        TextView textContent;
        WebView contentPicture;
        Button digg_photo;
        TextView digg_count;
        Button bury_photo;
        TextView bury_count;
        Button  comment_photo;
        TextView comment_count;

        public ViewHolder(View itemView) {
            super(itemView);
            photoView=itemView;
            headerPicture = (ImageView) itemView.findViewById(R.id.photo_header_picture);
            headerName = (TextView) itemView.findViewById(R.id.photo_header_name);
            textContent = (TextView) itemView.findViewById(R.id.photo_text_content);
            contentPicture = (WebView) itemView.findViewById(R.id.photo_picture);
            digg_photo=(Button)itemView.findViewById(R.id.digg_photo);
            digg_count=(TextView)itemView.findViewById(R.id.digg_count);
            bury_photo=(Button) itemView.findViewById(R.id.bury_photo);
            bury_count=(TextView)itemView.findViewById(R.id.bury_count);
            comment_photo=(Button)itemView.findViewById(R.id.comment_photo);
            comment_count=(TextView)itemView.findViewById(R.id.comment_count);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_recycle_adapter, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.contentPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击图片", Toast.LENGTH_SHORT).show();
            }
        });
        holder.digg_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    PhotoGroup photoGroup = photoGroupList.get(position);
                    int digg = Integer.parseInt(photoGroup.group.digg);
                    digg=digg+1;
                    String diggs=String.valueOf(digg);
                    holder.digg_photo.setBackgroundResource(R.drawable.dianzan_yellow);
                    holder.digg_count.setText(diggs);
                    holder.bury_photo.setBackgroundResource(R.drawable.buzan);
            }
        });
        holder.bury_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    PhotoGroup photoGroup = photoGroupList.get(position);
                    int digg = Integer.parseInt(photoGroup.group.bury);
                    digg=digg+1;
                    String diggs=String.valueOf(digg);
                    holder.bury_photo.setBackgroundResource(R.drawable.buzan_yellow);
                    holder.bury_count.setText(diggs);
                    holder.digg_photo.setBackgroundResource(R.drawable.dingding);
            }
        });
        holder.comment_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"hhh",Toast.LENGTH_SHORT).show();
            }
        });
        holder.contentPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"hhh",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PhotoGroup photoGroup = photoGroupList.get(position);
        holder.digg_count.setText(photoGroup.group.digg);
        holder.bury_count.setText(photoGroup.group.bury);
        holder.comment_count.setText(photoGroup.group.comment);
        Glide.with(context).load(photoGroup.group.user.userPicture).into(holder.headerPicture);
        holder.headerName.setText(photoGroup.group.user.name);
        String content="#"+photoGroup.group.category+'#'+' '+photoGroup.group.text;
      //  holder.textContent.setText(content);
        int start=0;
        int end = 0;
        for(int i=1;i<content.length();i++){
            if(content.charAt(i)=='#'){
                end=i+1;
                break;
            }
        }
        Log.d("nananannna", "onBindViewHolder: "+start+end);
        spannableString=new SpannableString(content);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF80AA")),start,end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        holder.textContent.setText(spannableString);
        holder.digg_photo.setBackgroundResource(R.drawable.dingding);
        holder.bury_photo.setBackgroundResource(R.drawable.buzan);
        //imageview自适应
//        para = (LinearLayout.LayoutParams) holder.contentPicture.getLayoutParams();
//        wm = (WindowManager) context
//                .getSystemService(Context.WINDOW_SERVICE);
//        para.height = photoGroup.group.photoImage.height;
//        para.width = 1050;
//        if(photoGroup.group.photoImage.height<500){
//            para.height=1050;
//        }
//        if(photoGroup.group.photoImage.height>1050){
//            para.height=1050;
//        }
//        holder.contentPicture.setLayoutParams(para);
        settings = holder.contentPicture.getSettings();
// 设置可任意缩放
       settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        holder.contentPicture.loadUrl(photoGroup.group.photoImage.photoUriLists.get(0).url);
//        holder.contentPicture.getSettings().setDefaultTextEncodingName("utf-8");// 避免中文乱码
//
//        holder.contentPicture.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//
//        settings.setJavaScriptEnabled(true);
//
//        settings.setNeedInitialFocus(false);
//
//        settings.setSupportZoom(true);
//
//        settings.setLoadWithOverviewMode(true);//适应屏幕
//
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//
//        settings.setLoadsImagesAutomatically(true);//自动加载图片
//        if(photoGroup.group.photoImage.height<400||photoGroup.group.photoImage.width<400){


//            // 获得图片的宽高
//            int width = photoGroup.group.photoImage.width;
//            int height = photoGroup.group.photoImage.height;
//            // 设置想要的大小
//            int newWidth = 900;
//            int newHeight = 900;
//            // 计算缩放比例
//            float scaleWidth = ((float) newWidth) / width;
//            float scaleHeight = ((float) newHeight) / height;
//            // 取得想要缩放的matrix参数
//            Matrix matrix = new Matrix();
//            matrix.postScale(scaleWidth, scaleHeight);
//            // 得到新的图片
//            Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
//                    true);
//            holder.contentPicture.setImageBitmap(newbm);
//        }else {
//            Glide.with(context).load(photoGroup.group.photoImage.photoUriLists.get(0).url).into(holder.contentPicture);
//        }
       // Glide.with(context).load(photoGroup.group.photoImage.photoUriLists.get(0).url).into(holder.contentPicture);
    }

    @Override
    public int getItemCount() {
        return photoGroupList.size();
    }

    public void add(PhotoGroup group, int position) {
        photoGroupList.add(position, group);
        notifyItemInserted(position);
    }

//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }
//
//    // 根据网络URL获取输入流
//    public InputStream getUrlInputStream(String strUrl) throws IOException {
//        URL url = new URL(strUrl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        InputStream inputStream = conn.getInputStream();
//        if (inputStream != null) {
//            return inputStream;
//        } else {
//            //Log.i("inputStream", "输入流对象为空");
//            return null;
//        }
//    }

//    // 将输入流转化为Bitmap流
//    public Bitmap getBitmap(InputStream inputStream) {
//        Bitmap bitmap = null;
//        if (inputStream != null) {
//            bitmap = BitmapFactory.decodeStream(inputStream);
//            return bitmap;
//        } else {
//            //  Log.i("test", "输入流对象in为空");
//            return null;
//        }

//    public static Bitmap ReadBitmapById(Context context, int resId)
//    {
//        BitmapFactory.Options opt = new BitmapFactory.Options();
//        opt.inPreferredConfig = Bitmap.Config.RGB_565;
//        opt.inPurgeable = true;
//        opt.inInputShareable = true;
//        InputStream is = context.getResources().openRawResource(resId);
//        return BitmapFactory.decodeStream(is, null, opt);
//    }

//    /**
//     * 根据图片的url路径获得Bitmap对象
//     * @param url
//     * @return
//     */
//    private Bitmap returnBitmap(String url) {
//        URL fileUrl = null;
//        Bitmap bitmap = null;
//
//        try {
//            fileUrl = new URL(url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            HttpURLConnection conn = (HttpURLConnection) fileUrl
//                    .openConnection();
//            conn.setDoInput(true);
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//
//    }

    }

