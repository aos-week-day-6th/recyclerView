package com.example.admin.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    GridAdapter gridAdapter;
    List<Product> products=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porduct);

        recyclerView=findViewById(R.id.recyclerView);
        gridAdapter=new GridAdapter(products,this);
        recyclerView.setLayoutManager(
                new GridLayoutManager(this,2)
        );
        recyclerView.setAdapter(gridAdapter);


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
                    "https://ae01.alicdn.com/kf/HTB19cHYXcfrK1RkSnb4q6xHRFXaB.jpg_350x350.jpg",
                    10.0
            ));
        }
        gridAdapter.updateProducts(this.products);
    }
}
