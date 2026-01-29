package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class OrderFragment extends Fragment {


    AppDatabase db;
    private static  final String myPreference = "preference";

    RecyclerView recyclerView;
    Button btnOrderToMain;
    List<OrderItem> items;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = AppDatabase.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_order, container, false);
        btnOrderToMain = view.findViewById(R.id.btnOrderToMainActivity);
        recyclerView = view.findViewById(R.id.orderRecyclerView);

        SharedPreferences prefs = requireActivity().getSharedPreferences(myPreference, MODE_PRIVATE);
        String name = prefs.getString("name", null);


        items = db.orderDao().getAllOrderDetail(name);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new MyOrderAdapter(requireContext().getApplicationContext(), items));



        btnOrderToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireActivity(), MainActivity.class);
                startActivity(i);
                requireActivity().finish();
            }
        });
        return view;
    }
}