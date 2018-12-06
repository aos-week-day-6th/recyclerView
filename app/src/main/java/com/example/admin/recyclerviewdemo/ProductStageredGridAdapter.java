package com.example.admin.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductStageredGridAdapter extends RecyclerView.Adapter<ProductStageredGridAdapter.ViewHolder> {

    List<Product> products;
    Context context;

    public ProductStageredGridAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(context).inflate(
               R.layout.item_staggered_grid_layout,parent,false
       );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product= products.get(position);

        Picasso.get().load(product.getImage())
                .placeholder(R.drawable.loading)
                .into(holder.image);

        holder.price.setText(product.getPrice()+"");


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.thumb);
            price=itemView.findViewById(R.id.price);

        }
    }
}
