package com.example.mevin.volleytest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mevin on 3/25/2019.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private Context context;
    private List<Detail> list;

    public DetailAdapter(Context context, List<Detail> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Detail detail = list.get(position);

        holder.textName.setText(detail.getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;

        public ViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.detail_name);
        }
    }

}