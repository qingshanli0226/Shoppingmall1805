package com.shopmall.bawei.shopmall1805.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shopmall.bawei.shopmall1805.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "aaaaaaa", Toast.LENGTH_SHORT).show();
    }
}
