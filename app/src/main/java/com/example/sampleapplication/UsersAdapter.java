package com.example.sampleapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserAdapterVH> {

    private List<Comments> comments1;
    private Context context;

    public UsersAdapter() {
    }

    public void setdata(List<Comments> comments1) {
        this.comments1 = comments1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_user_data,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {

        Comments comments = comments1.get(position);

        int id = comments.getId();
        int postId = comments.getPostId();
        String email = comments.getEmail();
        String body = comments.getBody();

        holder.id.setText(String.valueOf(id));
        holder.postId.setText(String.valueOf(postId));
        holder.email.setText(email);
        holder.body.setText(body);

    }

    @Override
    public int getItemCount() {
        return comments1.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {

        TextView id;
        TextView postId;
        TextView email;
        TextView body;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            postId = itemView.findViewById(R.id.postId);
            id = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.email);
            body = itemView.findViewById(R.id.body);
        }
    }
}
