package com.example.dealer1;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> list;
    Context context;
    ProductDataDao dao;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
        dao = ProductDataBase.getInstance(context).productDataDao();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {





        Product product = list.get(position);;



            holder.tvName.setText(product.name);
            holder.tvPrice.setText(String.valueOf(product.price));
        Uri uri = Uri.parse(list.get(position).getImageUri());


        Glide.with(holder.imageView.getContext())
                .load(uri)
                .placeholder(R.drawable.appicon)
                .error(R.drawable.appicon)
                .into(holder.imageView);



        holder.btnDelete.setOnClickListener(v -> {

            int pos = holder.getAdapterPosition();

            if (pos != RecyclerView.NO_POSITION) {
                list.remove(pos);
                dao.deleteProduct(product);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, list.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice;
        ImageView imageView;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageView = itemView.findViewById(R.id.imageView);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}

