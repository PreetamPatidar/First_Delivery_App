package com.example.dealer1;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static  final String myPreference = "preference";
    ProductDataBase db;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView welcome = findViewById(R.id.welcomeUser);
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnHelp = findViewById(R.id.btnHelp);
        Button btnCart = findViewById(R.id.btnCart);
        Button btnOrder = findViewById(R.id.btnOrder);
        LinearLayout layout = findViewById(R.id.MenuCartOrderLayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


      //  List<Product> items = new ArrayList<>();
      //  items.add(new Product("Onion", "This is onion from nashik and it is red in color and used for different purpose so this is a onion", 23,"res/drawable/onion.jpg"));
      //  items.add(new Product("Potato", "This is potato and it comes from the ground and grow in the ground it has different varities",50 ,"res/drawable/potato.jpg"));

      /*    db = ProductDataBase.getInstance(this);

      Executors.newSingleThreadExecutor().execute(() -> {

            List<Product> items =
                db.productDataDao().getAllProducts();


            this.runOnUiThread(() -> {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(
                        new MyAdapter(getApplicationContext(), items)
                );
            });
        });
        */

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        db = ProductDataBase.getInstance(getApplicationContext());

        new Thread(() -> {
            List<Product> list = db.productDataDao().getAllProducts();
            this.runOnUiThread(() -> {
                adapter = new MyAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);
            });
        }).start();

     //   recyclerView.setLayoutManager(new LinearLayoutManager(this));
     //   recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));



        SharedPreferences prefs = getSharedPreferences(myPreference, MODE_PRIVATE);
        String name = prefs.getString("name", null);
        welcome.setText("Welcome, " + name);


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));

            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main, new CartFragment())
                            .addToBackStack(null)
                            .commit();



            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main, new OrderFragment())
                            .addToBackStack(null)
                            .commit();
                }

            }
        });



            btnHelp.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {






                    if (savedInstanceState == null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, new SettingFragment())
                                .addToBackStack(null)
                                .commit();
                    }


                }

            });






    }
}