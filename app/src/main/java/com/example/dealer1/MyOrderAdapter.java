package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;



import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<OrderItem> orderItem;

    public MyOrderAdapter(Context context, List<OrderItem> orderItems) {

        this.context = context;
        this.orderItem = orderItems;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.orderview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderStatus.setText(orderItem.get(position).getStatus());
        holder.orderProductName.setText(orderItem.get(position).getProductName());
        holder.orderPrice.setText(String.valueOf(orderItem.get(position).getPrice()));
        holder.orderQuantity.setText(String.valueOf(orderItem.get(position).getQuantity()));



        holder.btnOrderTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return orderItem.size();
    }
}
