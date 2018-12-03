package com.example.admin.recyclerviewdemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity  implements PostAdapter.PostCallback {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> postList=new ArrayList<>();
    public static final int REQUEST_CODE_POST=1;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.btnPost:
                Intent intent=new Intent(this,NewPostActivity.class);
                startActivityForResult(intent,REQUEST_CODE_POST);
                return true;
            default: return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_POST && resultCode==RESULT_OK){
            //get data
            Post post = data.getParcelableExtra("data");
            // add new Item to recyclerView
            postList.add(0,post);
            adapter.notifyItemInserted(0);
            setRecyclerViewScroll(0);
            Log.e("new data",post.toString());
        }


    }

    private void setRecyclerViewScroll(int position){
        recyclerView.smoothScrollToPosition(position);
    }

    @Override
    public void getPost(Post post, int position) {
        postList.remove(post);
        adapter.notifyItemRemoved(position);
    }
}
