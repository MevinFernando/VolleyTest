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

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {

    private Context context;
    private List<Status> list;

    public StatusAdapter(Context context, List<Status> list) {
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
        Status status= list.get(position);

        holder.statusDescription.setText(status.getDescription());
        holder.statusTime.setText(status.getTime());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView statusDescription;
        public TextView statusTime;

        public ViewHolder(View itemView) {
            super(itemView);

           statusDescription= itemView.findViewById(R.id.status_description);
            statusTime = itemView.findViewById(R.id.status_time);

        }
    }

}