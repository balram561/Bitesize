package com.example.app2.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Interface.ItemClickListener;
import com.example.app2.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderAddress, txtOrderDistance;
    private ItemClickListener itemClickListener;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txtOrderAddress =(TextView) itemView.findViewById(R.id.order_address);
        txtOrderId =(TextView) itemView.findViewById(R.id.order_id);
        txtOrderPhone =(TextView) itemView.findViewById(R.id.order_phone);
        txtOrderStatus =(TextView) itemView.findViewById(R.id.order_status);
        txtOrderDistance = (TextView)itemView.findViewById(R.id.order_dist);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
