package com.example.dealer1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        Button btnAdminLogout = findViewById(R.id.btnAdminLogout);
        Button btnAdminProductManagement = findViewById(R.id.btnAdminProductManagment);
        TextView tv = findViewById(R.id.adminTv);

        RecyclerView recyclerView = findViewById(R.id.AdminRecyclerView);

        AppDatabase db = AppDatabase.getInstance(this);

        new Thread(() -> {
            List<OrderItem> items = db.orderDao().getAllOrderDetail();

            this.runOnUiThread( () ->  {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MyAdminAdapter(getApplicationContext(), items));
            });

        }).start();

        btnAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, SplashActivity.class));
            }
        });

        btnAdminProductManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdminLogout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                btnAdminProductManagement.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);




                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction
                                .replace(R.id.main1, new AdminAddProductFragment())
                                .commit();
            }
        });


    }
}