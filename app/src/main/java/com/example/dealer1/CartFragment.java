package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment {


    private static  final String myPreference = "preference";
    static AppDatabase db;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_cart, container, false);


        Button btnBackToMainActivity = view.findViewById(R.id.btnBackToMainActivity);
        Button btnCartToOrder = view.findViewById(R.id.btnCartToOrder);
        RecyclerView recyclerView = view.findViewById(R.id.CArtRecycleView);

        SharedPreferences prefs = requireActivity().getSharedPreferences(myPreference, MODE_PRIVATE);
        String name = prefs.getString("name", null);


        new Thread(() -> {
            List<CartItem> items = db.productDao().getAllDetail(name);

            requireActivity().runOnUiThread(() -> {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new MyCartAdapter(requireContext().getApplicationContext(), items));
            });
        }).start();

        btnBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireActivity(),MainActivity.class);
                startActivity(i);
                requireActivity().finish();
            }
        });

        btnCartToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().setContentView(R.layout.fragment_order);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.orderFragmentContainer, new OrderFragment())
                        .addToBackStack(null)
                        .commit();


            }
        });

        return view;
    }
}