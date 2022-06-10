package com.example.phonetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.submitButton)
    Button mButton;

    @BindView(R.id.EditText)
    TextInputLayout mtextInputEditText;

    boolean isAllFieldsChecked = true;
//    private DatagramSocket ButterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String view = mtextInputEditText.getEditText().getText().toString();
                Intent intent = new Intent(MainActivity.this, Products.class);
                Toast.makeText(MainActivity.this, "Hello"  + view, Toast.LENGTH_SHORT).show();
                intent.putExtra("create", view);
                startActivity(intent);
            }
        });
    }
}