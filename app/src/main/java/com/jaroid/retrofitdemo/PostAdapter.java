package com.jaroid.retrofitdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<PostResponse> mListPost;
    private Context mContext;

    public PostAdapter() {
    }

    public void setData(ArrayList<PostResponse> listPost) {
        this.mListPost = listPost;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        PostResponse postResponse = mListPost.get(position);

        Glide.with(mContext).load(postResponse.getCover()).into(holder.imgHeader);
        holder.tvContent.setText(postResponse.getBody());
        holder.tvTag.setText(postResponse.getTag());
        holder.tvTag.setBackgroundResource(getBackgroundColor(postResponse.getTag()));
    }

    private int getBackgroundColor(String tag) {
        switch (tag) {
            case "one":
                return R.color.tag_one;
            case "two":
                return R.color.tag_two;
            case "three":
            default:
                return R.color.tag_three;
        }
    }

    @Override
    public int getItemCount() {
        return mListPost.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHeader;
        TextView tvTag, tvContent;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHeader = itemView.findViewById(R.id.imgHeader);
            tvTag = itemView.findViewById(R.id.tvTag);
            tvContent = itemView.findViewById(R.id.tvContent);

        }
    }
}
