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

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{

    private List<Product> products;
    private Context context;

    public GridAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).
                inflate(R.layout.item_grid_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product=products.get(position);
        //bind Image view by using picaso
        Picasso.get().load(product.getImage())
                .centerCrop()
                .resize(200,200)
                .placeholder(R.drawable.loading)
                .into(holder.image);

        holder.price.setText(""+product.getPrice());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.thumb);
            price =itemView.findViewById(R.id.price);
        }
    }

    public void updateProducts(List<Product> products){
        this.products.addAll(products);
        notifyDataSetChanged();
    }
}
