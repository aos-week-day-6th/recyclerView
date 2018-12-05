package com.example.admin.recyclerviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity  implements PostAdapter.PostCallback {
    public static final int REQUEST_CODE_POST=1;
    private static final int REQUEST_CODE_EDIT =2;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> postList=new ArrayList<>();

    //paginate vars
    int totalPage=10;
    boolean isLoading=true;
    int currentPage=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PostAdapter(this,postList);

        //recyclerView Animator
        //AlphaInAnimationAdapter alphaAdapter= new AlphaInAnimationAdapter(adapter);

        recyclerView.setAdapter(adapter);

        isLoading=true;
        currentPage=1;
        Paginate.with(recyclerView,callback)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .setLoadingListItemSpanSizeLookup(new LoadingListItemSpanLookup() {
                    @Override
                    public int getSpanSize() {
                        return 1;
                    }
                }).build();

        //getData();
    }


    Paginate.Callbacks callback = new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            Log.e("page", "onLoadMore: "+currentPage );
            //isLoading=true;
            if(isLoading){
                new Handler().postDelayed(()-> {
                    getData();
                },2000);

                currentPage++;
                isLoading=false;
            }
        }


        @Override
        public boolean isLoading() {
            return false;
        }

        @Override
        public boolean hasLoadedAllItems() {
            return currentPage==10;
        }
    };

    List<Post> newPosts=new ArrayList<>();
    private void getData() {
        newPosts.clear();
        for (int i=0;i<10;i++){
            newPosts.add(new Post(
                    R.drawable.puppy,
                    "___Puppy___",
                    "Phnom Penh",
                    "As this is just a custom ImageView and not a custom Drawable or a combination of both, it can be used with all kinds of ",
                    "100 111 k",
                    R.drawable.puppy
            ));
        }

        isLoading=true;
        adapter.setPosts(newPosts);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);

        MenuItem searchItem= menu.findItem(R.id.search);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                doSearch(s);
                return true;
            }
        });

        return true;
    }

    private void doSearch(String s) {

        Log.e("search", "doSearch: "+s);
        if(s.isEmpty()){
            postList.clear();
            getData();
            return;
        }

        List<Post> searchResult=new ArrayList<>();
        searchResult.clear();
        for(Post post: postList){
            if(post.getContent().contains(s)){
                searchResult.add(post);
            }
        }

        if(searchResult.size()>0){
            adapter.setSearchResult(searchResult);
        }

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

        }else if(requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK){
            Post newPost=data.getParcelableExtra("newPost");
            int position = data.getIntExtra("position",0);
            postList.set(position,newPost);
            adapter.notifyItemChanged(position);
            setRecyclerViewScroll(position);
        }




    }

    private void setRecyclerViewScroll(int position){
        recyclerView.smoothScrollToPosition(position);
    }

    @Override
    public void getPost(int actionType,Post post, int position) {
        switch (actionType){
            case PostAdapter.TYPE_REMOVE:
                postList.remove(post);
                adapter.notifyItemRemoved(position);
                break;
            case PostAdapter.TYPE_EDIT:
                Log.e("position",position + "");
                 Intent intent=new Intent(this,EditPostActivity.class);
                 Bundle b=new Bundle();
                 b.putParcelable("oldPost",post);
                 intent.putExtras(b);
                 intent.putExtra("position",position);
                 startActivityForResult(intent,REQUEST_CODE_EDIT);
                break;
        }

    }
}
