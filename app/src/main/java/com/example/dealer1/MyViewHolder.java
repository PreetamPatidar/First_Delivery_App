package com.example.dealer1;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView title, price, description, cartPrice, cartTitle, cartQuantity, orderStatus, orderPrice, orderQuantity, orderProductName, adminUserName, adminProductName, adminPrice, adminQuantity, adminStatus;

    EditText quantity;

    Button btnBuy, btnCartBuy, btnCartRemove, btnOrderTrack, btnChangeStatus;


    public MyViewHolder(View itemView) {
        super(itemView);

        quantity = itemView.findViewById(R.id.quantities);

        imageView = itemView.findViewById(R.id.imageView);
        price = itemView.findViewById(R.id.price);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        btnBuy = itemView.findViewById(R.id.btnBuy);

        btnCartBuy = itemView.findViewById(R.id.btnCartBuy);
        cartPrice =itemView.findViewById(R.id.cartProductPrice);
        cartQuantity = itemView.findViewById(R.id.cartProductQuantity);
        cartTitle = itemView.findViewById(R.id.cartProductTitle);

        btnCartRemove = itemView.findViewById(R.id.btnCartRemove);

        btnOrderTrack = itemView.findViewById(R.id.btnOrderTrack);
        orderPrice = itemView.findViewById(R.id.orderPrice);
        orderStatus = itemView.findViewById(R.id.orderStatus);
        orderQuantity = itemView.findViewById(R.id.orderQuantity);
        orderProductName = itemView.findViewById(R.id.orderProductName);


        adminPrice = itemView.findViewById(R.id.adminPrice);
        adminProductName = itemView.findViewById(R.id.adminProductname);
        adminQuantity = itemView.findViewById(R.id.adminQuantity);
        adminStatus = itemView.findViewById(R.id.adminStatus);
        adminUserName = itemView.findViewById(R.id.adminUserName);
        btnChangeStatus = itemView.findViewById(R.id.btnChangeStatus);








    }
}
