package com.example.admin.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Post> posts;
    Context context;
    PostCallback callback;
    public static final int TYPE_EDIT=2;
    public static final int TYPE_REMOVE=1;

    public PostAdapter(Context context,List<Post> posts) {
        this.posts = posts;
        this.context=context;
        callback = (PostCallback) context;
    }


    public void setPosts(List<Post> posts) {
        this.posts.addAll( posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(
                R.layout.post_item_layout,viewGroup,false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post=posts.get(i);
        viewHolder.imageProfile.setImageResource(post.getUserProfile());
        viewHolder.image.setImageResource(post.getImage());
        viewHolder.userName.setText(post.getUserName());
        viewHolder.content.setText(post.getContent());
        viewHolder.checkIn.setText(post.getCheckIn());
        viewHolder.likeCount.setText(post.getLikeCount());
        viewHolder.onImageClicked();
        viewHolder.onItemMenuClicked(post);
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public void setSearchResult(List<Post> searchResult) {
        posts.clear();
        posts.addAll(searchResult);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageProfile, image,btnMenu,btnLike,btnComment,btnShare,btnBookmark;
        TextView userName,content,checkIn,likeCount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile=itemView.findViewById(R.id.imageProfile);
            image=itemView.findViewById(R.id.image);
            btnMenu=itemView.findViewById(R.id.btnMenu);
            btnLike=itemView.findViewById(R.id.like);
            btnComment=itemView.findViewById(R.id.comment);
            btnShare=itemView.findViewById(R.id.share);
            btnBookmark=itemView.findViewById(R.id.bookmark);

            userName=itemView.findViewById(R.id.userName);
            content=itemView.findViewById(R.id.content);
            checkIn=itemView.findViewById(R.id.checkIn);
            likeCount=itemView.findViewById(R.id.likeCount);
        }


        private void onImageClicked(){
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,MainActivity.class));
                }
            });
        }

        private void onItemMenuClicked(Post post){
            btnMenu.setOnClickListener(v->{
                PopupMenu menu=new PopupMenu(context,v);
                menu.inflate(R.menu.popup_menu);
                menu.setOnMenuItemClickListener(item->{
                    switch (item.getItemId()){
                        case R.id.remove:
                            //callback
                            callback.getPost(TYPE_REMOVE,post,getAdapterPosition());
                            return true;
                        case R.id.editPost:
                            callback.getPost(TYPE_EDIT,post,getAdapterPosition());
                            return  true;
                        default: return false;
                    }
                });
                menu.show();
            });
        }

    }

    public interface  PostCallback{
        void getPost(int actionType,Post post, int position);
    }
}
