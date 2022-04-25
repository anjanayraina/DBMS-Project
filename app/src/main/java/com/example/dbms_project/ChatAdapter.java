package com.example.dbms_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<ChatDisplayModel> userList = new ArrayList<>();



    public ChatAdapter(List<ChatDisplayModel>userList  ) {
        this.userList=userList;


    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_chats_design,parent,false);
        return new ViewHolder(view );


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String name=userList.get(position).getName();

        String line=userList.get(position).getLine();

        holder.setData(name,line);



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    //view holder class



    public class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private TextView textview3;
        private TextView divider;




        public ViewHolder(@NonNull View itemView)  {
            super(itemView);
            textView=itemView.findViewById(R.id.textview);
            divider=itemView.findViewById(R.id.Divider);


        }

        public void setData( String name, String line) {


            textView.setText(name);

            divider.setText(line);

        }



    }


}
