package com.pb.app.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= (ImageView) findViewById(R.id.imageView);
        Glide.with(this).load("http://image.tianjimedia.com/uploadImages/2012/233/38/H439I0N71ARI.jpg").into(iv);
    }
}
