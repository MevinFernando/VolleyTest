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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<Item> list;

    public ItemAdapter(Context context, List<Item> list) {
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
        Item item = list.get(position);

        holder.textName.setText(item.getName());
        holder.textPKD.setText(item.getPkd());
        holder.textQTY.setText(item.getMrp());
        holder.textMRP.setText(item.getQty());
        holder.textReason.setText(item.getReason());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;
        public TextView textPKD;
        public TextView textMRP;
        public TextView textQTY;
        public TextView textReason;

        public ViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.item_name);
            textPKD = itemView.findViewById(R.id.item_PKD);
            textMRP = itemView.findViewById(R.id.item_MRP);
            textQTY = itemView.findViewById(R.id.item_QTY);
            textReason = itemView.findViewById(R.id.item_Reason);
        }
    }

}