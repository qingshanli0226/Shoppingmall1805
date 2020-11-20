package com.shopmall.bawei.shopmall1805;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class WelcomActivity extends AppCompatActivity {
    private ImageView ImageOne;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImageOne = (ImageView) findViewById(R.id.Image_one);
        ImageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
