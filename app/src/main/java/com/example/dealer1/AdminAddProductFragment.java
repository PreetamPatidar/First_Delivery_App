package com.example.dealer1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.concurrent.Executors;

public class AdminAddProductFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    ImageView productImage;
    Button selectImageBtn, addBtn, showbtn;
    EditText nameEdt, priceEdt, descEdt;

    ProductDataBase db;
    Uri imageUri; // ✅ class-level URI

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_add_product, container, false);

        productImage = view.findViewById(R.id.productImage);
        selectImageBtn = view.findViewById(R.id.selectImageBtn);
        addBtn = view.findViewById(R.id.addProductBtn);
        showbtn = view.findViewById(R.id.showProductbtn);

        nameEdt = view.findViewById(R.id.productName);
        priceEdt = view.findViewById(R.id.productPrice);
        descEdt = view.findViewById(R.id.productDesc);

        db = ProductDataBase.getInstance(requireContext());
        Handler handler = new Handler(Looper.getMainLooper());

        selectImageBtn.setOnClickListener(v -> openGallery());

        addBtn.setOnClickListener(v -> {
            String name = nameEdt.getText().toString();
            String desc = descEdt.getText().toString();
            double price = Double.parseDouble(priceEdt.getText().toString());

            String imageUriString = imageUri != null ? imageUri.toString() : "";

            Executors.newSingleThreadExecutor().execute(() -> {
                db.productDataDao().insertProduct(
                        new Product(name, desc, price, imageUriString)
                );

                handler.post(() ->
                        Toast.makeText(getContext(),
                                "Product Added Successfully",
                                Toast.LENGTH_SHORT).show()
                );
            });
        });

        showbtn.setOnClickListener(v ->
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.main1, new AdminProductListFragment())
                        .addToBackStack(null)
                        .commit()
        );

        return view;
    }

    // ✅ CORRECT POSITION
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST &&
                resultCode == Activity.RESULT_OK &&
                data != null) {

            imageUri = data.getData(); // ✅ assign to class variable

            requireContext().getContentResolver().takePersistableUriPermission(
                    imageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            );

            Glide.with(requireContext())
                    .load(imageUri)
                    .placeholder(R.drawable.image_placeholder)
                    .into(productImage);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
}
