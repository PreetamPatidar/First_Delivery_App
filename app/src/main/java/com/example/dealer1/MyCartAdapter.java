package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


public class MyCartAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static  final String myPreference = "preference";


    List<CartItem> cartItems;
     AppDatabase db;
    AppDatabase db1;

    Context context;
    public MyCartAdapter(Context context, List<CartItem> cartItems) {

        this.context = context;
        this.cartItems = cartItems;
        db = AppDatabase.getInstance(context);
        db1 = AppDatabase.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cartview, parent, false));
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        try {

            int position1 = holder.getAdapterPosition();

            if (position1 != RecyclerView.NO_POSITION && cartItems.size() > position1) {

                holder.cartTitle.setText(cartItems.get(position).getTitle());
                holder.cartPrice.setText(String.valueOf(cartItems.get(position).getPrice()));
                holder.cartQuantity.setText(String.valueOf(cartItems.get(position).getQuantity()));

            }
            holder.btnCartBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int price = cartItems.get(holder.getPosition()).getPrice();

                    String title = cartItems.get(holder.getPosition()).getTitle();

                    int quantity = cartItems.get(holder.getPosition()).getQuantity();


                    SharedPreferences prefs = context.getSharedPreferences(myPreference, MODE_PRIVATE);
                    String name = prefs.getString("name", null);



                    OrderDao dao = db1.orderDao();


                    dao.insertOrder(new OrderItem(price, quantity, title, name, "pending"));
                    Toast.makeText(context, "Item Add successfully in the order", Toast.LENGTH_SHORT).show();


                    db.productDao().deleteProduct(cartItems.get(holder.getPosition()).getTitle(),
                            String.valueOf(cartItems.get(holder.getPosition()).getQuantity()));
                    cartItems.remove(holder.getPosition());

                    notifyItemRemoved(holder.getPosition());
                    NotificationHelper.showNotification(
                            context,
                            "Order Confirmed",
                            "Your order has been confirmed successfully" +
                                    "Detail" +
                                    "your " + title + " for quantity " + quantity + " at price " + price + " is added to order Successfully"

                    );


                }
            });

            holder.btnCartRemove.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View v) {


                    db.productDao().deleteProduct(cartItems.get(holder.getPosition()).getTitle(),
                            String.valueOf(cartItems.get(holder.getPosition()).getQuantity()));


                    cartItems.remove(holder.getPosition());
                    notifyItemRemoved(holder.getPosition());


                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
