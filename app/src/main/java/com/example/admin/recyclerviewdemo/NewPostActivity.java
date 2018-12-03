package com.example.admin.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NewPostActivity extends AppCompatActivity
implements View.OnClickListener {

    EditText text;
    ImageView image;
    Button btnPostNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        text=findViewById(R.id.text);
        image=findViewById(R.id.image);
        btnPostNow=findViewById(R.id.btnPost);

        btnPostNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnPost){
            Post post=new Post(
                    R.drawable.puppy,
                    "__Puppy__",
                    "KPS",
                    text.getText().toString(),
                    "0",
                    R.drawable.puppy);
            Intent intent =new Intent();
            Bundle bundle=new Bundle();
            bundle.putParcelable("data",post);
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
