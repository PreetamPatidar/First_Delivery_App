package com.example.dealer1;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productTable")
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public int price;
     public int quantity;

     public String userName;

     public String status;


    public CartItem(int price, int quantity, String title, String userName, String status) {


        this.price = price;
        this.quantity = quantity;
        this.title = title;
        this.userName = userName;
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
