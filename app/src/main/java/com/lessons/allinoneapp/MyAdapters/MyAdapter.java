package com.lessons.allinoneapp.MyAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.allinoneapp.DetailActivity;
import com.lessons.allinoneapp.MyConstants.AllConstants;
import com.lessons.allinoneapp.MyData.SocialData;
import com.lessons.allinoneapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Toast mToast;

    private List<SocialData> data;
    private Context context;

    public MyAdapter(List<SocialData> data, Context context) {
        this.data = data;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final SocialData mData = data.get(position);
        holder.title.setText(mData.txt);

        holder.icon.setImageResource(mData.imgId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                AllConstants.LINK = mData.getTxt();


                if(mToast!= null){
                    mToast.cancel();
                }

                mToast = Toast.makeText(context, AllConstants.LINK+" clicked", Toast.LENGTH_SHORT);

                mToast.show();

                final Intent intent;
                intent = new Intent(context, DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
            }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public  ImageView icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            icon = itemView.findViewById(R.id.iconView);

        }
    }
}
