package com.example.dealer1;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insertOrder(OrderItem orderItem);

    @Query("SELECT * FROM orderTable WHERE userName = :userName")
    List<OrderItem> getAllOrderDetail(String userName);

    @Query("SELECT * FROM orderTable")
    List<OrderItem> getAllOrderDetail();

    @Query("UPDATE orderTable SET status = :newStatus " +
            "WHERE username = :username " +
            "AND productName = :productName " +
            "AND quantity = :quantity")
    void updateOrderStatus(String newStatus,
                           String username,
                           String productName,
                           int quantity);


}
