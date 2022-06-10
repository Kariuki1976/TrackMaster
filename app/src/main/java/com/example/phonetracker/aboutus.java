package com.example.phonetracker;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;

public class aboutus extends AppCompatActivity {
@BindView(R.id.takeMe)
    Button mtakeMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ButterKnife.bind(this);
        mtakeMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeMe();
            }
        });
    }
    public void takeMe(){
        Intent intent = new Intent(this, Products.class);
        startActivity(intent);
    }
}