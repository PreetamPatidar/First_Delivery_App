package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static java.security.AccessController.getContext;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Product> item;
    AppDatabase db;

    private static  final String myPreference = "preference";


    public MyAdapter(Context context, List<Product> item) {
        this.context = context;
        this.item = item;
        db = AppDatabase.getInstance(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_items, parent, false));
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {




        holder.title.setText(item.get(position).getName());
        holder.description.setText(item.get(position).getDescription());
        holder.price.setText("Price ₹"+ item.get(position).getPrice());


        Uri uri = Uri.parse(item.get(position).getImageUri());




        Glide.with(holder.imageView.getContext().getApplicationContext())
                .load(uri)
                .placeholder(R.drawable.appicon)
                .error(R.drawable.potato)
                .into(holder.imageView);



        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double price = item.get(holder.getPosition()).getPrice();
                String title = item.get(holder.getPosition()).getName();
                String quantity1 = holder.quantity.getText().toString().trim();

                if(quantity1.isEmpty()){
                    Toast.makeText(context, "Enter the Quantity To add Cart", Toast.LENGTH_SHORT).show();
                }else {
                    try {


                        int quantity = Integer.parseInt(quantity1);

                        int totalPrice = (int) price * quantity;

                        holder.price.setText("Price ₹ " + totalPrice);


                        SharedPreferences prefs = context.getSharedPreferences(myPreference, MODE_PRIVATE);
                        String name = prefs.getString("name", null);


                        ProductDao dao = db.productDao();

                        dao.insertProduct(new CartItem(totalPrice, quantity, title, name, "pending"));
                        Toast.makeText(context, "Item Add successfully in the Cart", Toast.LENGTH_SHORT).show();
                        NotificationHelper.showNotification(
                                context,
                                "one Item added to Cart",
                                "your " + title + " for quantity " + quantity + " at price " + price + " is added to cart Successfully"
                        );
                    }catch (NumberFormatException e){
                        Toast.makeText(context, "Invalid Quantity", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });



    }

;

    @Override
    public int getItemCount() {
        return item.size();
    }
}



