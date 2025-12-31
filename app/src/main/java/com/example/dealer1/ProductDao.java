package com.example.dealer1;


import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(CartItem cartItem);



    @Query("SELECT * FROM productTable")
    List<CartItem> getAllDetail();

    @Query("SELECT * FROM productTable Where userName = :username")
    List<CartItem> getAllDetail(String username);


    @Query("DELETE FROM productTable WHERE title = :title AND quantity =:quantity")
    void deleteProduct(String title, String quantity);



}
