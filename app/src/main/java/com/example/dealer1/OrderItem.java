package com.example.dealer1;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orderTable")
public class OrderItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String productName;
    public int price;
    public int quantity;

    public String userName;

    public String status;




    public OrderItem(int price, int quantity, String productName, String userName, String status) {


        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String title) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
