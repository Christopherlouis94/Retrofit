package com.test.retrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.retrofit.Dataclass.User;
import com.test.retrofit.Dataclass.UserList;
import com.test.retrofit.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserList.Datum> users;
    private Context mCtx;
    public UserAdapter(List<UserList.Datum> users, Context mCtx) {
        this.users = users;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_users, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        UserList.Datum user = users.get(position);
        holder.textViewName.setText(user.first_name+" "+user.last_name);
        Glide.with(mCtx)
                .load(user.avatar)
//                    .placeholder(R.drawable.uaerdummmy)
                // optional
                .into(holder.imageButtonMessage);
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageButton imageButtonMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageButtonMessage = (ImageButton) itemView.findViewById(R.id.imageButtonMessage);
        }
    }



}