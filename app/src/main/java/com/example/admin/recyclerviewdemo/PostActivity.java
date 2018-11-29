package com.example.admin.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> postList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PostAdapter(this,postList);

        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        for (int i=0;i<50;i++){
            this.postList.add(new Post(
                    R.drawable.puppy,
                    "___Puppy___",
                    "Phnom Penh",
                    "As this is just a custom ImageView and not a custom Drawable or a combination of both, it can be used with all kinds of ",
                    "100 111 k",
                    R.drawable.puppy
            ));
        }

        adapter.setPosts(this.postList);

    }
}
