package com.example.asm_thinhndph18079.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_thinhndph18079.R;
import com.example.asm_thinhndph18079.model.Photo;
import com.example.asm_thinhndph18079.onClick.OnClickPhoto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<Photo> mListPhoto;
    private Context mContext;
    private OnClickPhoto onClickPhoto;


    public PhotoAdapter(List<Photo> mListPhoto, Context mContext, OnClickPhoto onClickPhoto) {
        this.mListPhoto = mListPhoto;
        this.mContext = mContext;
        this.onClickPhoto = onClickPhoto;
    }

    public void setData(List<Photo> mListPhoto) {
        this.mListPhoto = mListPhoto;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = mListPhoto.get(position);
        Glide.with(mContext).load(photo.getUrlC()).placeholder(R.drawable.loading).into(holder.img);
        holder.tv.setText(photo.getViews() + " views");
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPhoto.onClickShowPhoto(photo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListPhoto.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private CardView cv;
        private TextView tv;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            cv = itemView.findViewById(R.id.cardView);
            tv = itemView.findViewById(R.id.id);
        }
    }
}
