package com.example.admin.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditPostActivity extends AppCompatActivity {

    EditText checkIn,content;
    Button btnSaveChange;
    Post post;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        checkIn=findViewById(R.id.checkIn);
        content=findViewById(R.id.content);
        btnSaveChange=findViewById(R.id.btnSaveChange);

        Intent intent=getIntent();
        if(intent!=null){
            post=intent.getParcelableExtra("oldPost");
            position=intent.getIntExtra("position",0);
            checkIn.setText(post.getCheckIn());
            content.setText(post.getContent());
        }

        btnSaveChange.setOnClickListener(v->{
            /*Post newPost=new Post(
                    post.getUserProfile(),
                    post.getUserName(),
                    checkIn.getText().toString(),
                    content.getText().toString(),
                    post.getLikeCount(),
                    post.getImage()
            );*/
            post.setCheckIn(checkIn.getText().toString());
            post.setContent(content.getText().toString());
            Intent i=new Intent();
            Bundle b=new Bundle();
            b.putParcelable("newPost",post);
            i.putExtra("position",position);
            i.putExtras(b);
            setResult(RESULT_OK,i);
            finish();
        });
    }

}
