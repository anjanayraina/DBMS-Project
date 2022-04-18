package com.example.dbms_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private List<ModelText> userList ;
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_design , parent , false);
      return new ViewHolder(view);

    }

    public Adapter(List<ModelText> userList){


        this.userList = userList;


    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int imaPos = userList.get(position).getImage();
        String userName = userList.get(position).getName();
        String messsage = userList.get(position).getText();
        String msgTime =  userList.get(position).getTime();
        String partition=  userList.get(position).getLine();
        holder.setData(imaPos , userName , messsage , msgTime , partition);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView uName ;
        TextView msg ;
        TextView mesgTime ;
        TextView lines;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            uName = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.text);
            mesgTime  =itemView.findViewById(R.id.time);
            lines = itemView.findViewById(R.id.line);

        }

        public void setData(int resource  , String name  , String text,  String time ,  String l){

            this.img.setImageResource(resource);
            this.uName.setText(name);
            this.msg.setText(text);
            this.mesgTime.setText(time);
            this.lines.setText(l);


        }
    }
}
