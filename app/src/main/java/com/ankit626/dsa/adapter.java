package com.ankit626.dsa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ankit Sharma on 11-06-2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.Viewholder> {
    public adapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    List<User> list;
    Context context;


    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        User user=list.get(position);
    holder.t1.setText(user.getT1());
    holder.t2.setText(user.getT2());
    holder.t3.setImageResource(user.getT3());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView t1;
        public TextView t2;
        public ImageView t3;
        public Viewholder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.t1);
            t2=(TextView)itemView.findViewById(R.id.t2);
            t3=(ImageView)itemView.findViewById(R.id.uy);
        }
    }
}
