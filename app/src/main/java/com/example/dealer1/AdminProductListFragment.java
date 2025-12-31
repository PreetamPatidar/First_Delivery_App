package com.example.dealer1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdminProductListFragment extends Fragment {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    ProductDataBase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_product_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        db = ProductDataBase.getInstance(requireContext());

        new Thread(() -> {
            List<Product> list = db.productDataDao().getAllProducts();
            requireActivity().runOnUiThread(() -> {
                adapter = new ProductAdapter(getContext(), list);
                recyclerView.setAdapter(adapter);
            });
        }).start();

        return view;
    }
}
