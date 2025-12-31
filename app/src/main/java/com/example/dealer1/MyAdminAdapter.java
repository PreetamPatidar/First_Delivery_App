package com.example.dealer1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdminAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<OrderItem> list;
    AppDatabase db;

    public MyAdminAdapter(Context context, List<OrderItem> list) {
        this.context = context;
        this.list = list;
        db = AppDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adminview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.adminStatus.setText(list.get(position).getStatus());
        holder.adminProductName.setText(list.get(position).getProductName());
        holder.adminPrice.setText(String.valueOf(list.get(position).getPrice()));
        holder.adminQuantity.setText(String.valueOf(list.get(position).getQuantity()));
        holder.adminUserName.setText(list.get(position).getUserName());



        holder.btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderDao dao = db.orderDao();

                if(list.get(holder.getPosition()).getStatus().equals("pending")){

                    new Thread(()-> dao.updateOrderStatus("Confirm",
                            list.get(holder.getPosition()).getUserName(),
                            list.get(holder.getPosition()).getProductName(),
                            list.get(holder.getPosition()).getQuantity())).start();

                    list.get(holder.getPosition()).status = "Confirm";

                    Toast.makeText(context, "order is confirm", Toast.LENGTH_SHORT).show();



                    notifyItemChanged(holder.getPosition());


                }
                else{

                    dao.updateOrderStatus("pending",
                            list.get(holder.getPosition()).getUserName(),
                            list.get(holder.getPosition()).getProductName(),
                            list.get(holder.getPosition()).getQuantity());

                    list.get(holder.getPosition()).status = "pending";

                    Toast.makeText(context, "order is in pending", Toast.LENGTH_SHORT).show();
                     notifyItemChanged(holder.getPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
