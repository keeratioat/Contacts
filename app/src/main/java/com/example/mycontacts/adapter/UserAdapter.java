package com.example.mycontacts.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.R;
import com.example.mycontacts.model.User;
import com.example.mycontacts.util.DateFormatter;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context mContext;
    private  User[] mUser;
    public UserAdapter(Context context , User[] users) {
        this.mContext = context;
        this.mUser = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).
            inflate(R.layout.item_user,parent,false);
    return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user =  mUser[position];
        holder.fullNameTextView.setText(user.firstName + " " + user.lastName);
        holder.brithDateTextView.setText(DateFormatter.formatDateForUi(user.brithDate));
                /*if(user.singel){
            holder.singleTextView.setBackgroundColor(Color.RED);

        }else{
            holder.singleTextView.setBackgroundColor(Color.WHITE);
        }*/
        holder.singleTextView.setBackgroundColor(user.singel ?  Color.RED : Color.WHITE);
       /* if(user.gender == User.GENDER_MALE){
            holder.genderImageView.setImageResource(R.drawable.male);
        }else{
            holder.genderImageView.setImageResource(R.drawable.female);
        }*/
        holder.genderImageView.setImageResource(
                user.gender == User.GENDER_MALE ? R.drawable.male : R.drawable.female
        );
    }

    @Override
    public int getItemCount() {
        return mUser.length;
    }

     static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fullNameTextView;
        TextView brithDateTextView;
        ImageView genderImageView;
        TextView singleTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           this.fullNameTextView = itemView.findViewById(R.id.full_name_text_view);
           this.brithDateTextView = itemView.findViewById(R.id.birth_date_text_view);
           this.genderImageView = itemView.findViewById(R.id.gender_image_view);
           this.singleTextView = itemView.findViewById(R.id.single_text_view);
        }
    }
}
