package com.example.week3.view;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3.R;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView,imageView_back;
    private TextView text_product,text_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = findViewById(R.id.details_image);
        imageView_back = findViewById(R.id.back);
        text_product = findViewById(R.id.details_product);
        text_price = findViewById(R.id.details_price);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String product = intent.getStringExtra("product");
        String price = intent.getStringExtra("price");

        Glide.with(DetailsActivity.this).load(image).into(imageView);
        text_product.setText(product);
        text_price.setText("￥"+price);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
