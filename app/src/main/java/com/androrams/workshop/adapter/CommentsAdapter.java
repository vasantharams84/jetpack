package com.androrams.workshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androrams.workshop.R;
import com.androrams.workshop.model.Comment;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    Context mCtx;
    List<Comment> comments;

    public CommentsAdapter(Context mCtx, List<Comment> comments) {
        this.mCtx = mCtx;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.comments_list_item, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comment comment = comments.get(position);

        holder.textView.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public CommentsViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.message);
        }
    }
}