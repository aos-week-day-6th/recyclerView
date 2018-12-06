package com.example.admin.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ProductStaggeredActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product> products=new ArrayList<>();
    ProductStageredGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_staggered);


        recyclerView=findViewById(R.id.recyclerView);
        adapter=new ProductStageredGridAdapter(products,this);
        recyclerView.setLayoutManager(
               new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {

        for(int i=0;i<20;i++){
            this.products.add(new Product(
                    "https://ae01.alicdn.com/kf/HTB1VHZ9XinrK1Rjy1Xcq6yeDVXau.jpg_350x350.jpg",
                    25.0
            ));

            this.products.add(new Product(
                    "https://ae01.alicdn.com/kf/HTB15iDeX.jrK1RkHFNRq6ySvpXa7.jpg_350x350.jpg",
                    8.0
            ));

            this.products.add(new Product(
                    "https://www.spendwithpennies.com/wp-content/uploads/2018/06/orange-chicken-www.thereciperebel.com-SWP-9.jpg",
                    10.0
            ));
        }
        adapter.updateProducts(this.products);
    }
}
