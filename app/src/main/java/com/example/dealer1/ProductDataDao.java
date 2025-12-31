package com.example.dealer1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDataDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM products ORDER BY id DESC")
    List<Product> getAllProducts();

    @Delete
    void deleteProduct(Product product);

    @Query("DELETE FROM products")
    void deleteAll();
}
