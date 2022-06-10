package com.example.phonetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Products extends AppCompatActivity {
    @BindView(R.id.editTextTextPersonName) EditText mName;
    @BindView(R.id.productListView) ListView mListView;
    @BindView(R.id.hyperlinkTextView) TextView mLink;
    @BindView(R.id.contactUs)
    ImageButton mImageButton;

    private String[] phones = new String[] {"Sumsung", "Tecno","Nokia", "Itel","Xiaomi","Iphone","Sony","Huawei","others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        String view = getIntent().getStringExtra("create");
        mName.setText(view);
//        getIntent().putExtra("slim", view);


        //adding a builder dialogue
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(fm, "Sample Fragment");


        setupHyperlink();// calling the hyperlink method

        //Initializing Array adapter
//        ArrayAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_list_item_1,phones);
        ProductsArrayAdapter adapter = new ProductsArrayAdapter(this, android.R.layout.simple_list_item_1,phones);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone = ((TextView)view).getText().toString();
                Toast.makeText(Products.this, phone, Toast.LENGTH_LONG).show();
            }
        });

        //contact us navigation
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(Products.this, aboutus.class);

                startActivity(intent1);
            }
        });


        //Fetching user input from the MainActivity
//        Intent intent = new Intent(EditText, );
//        String name = intent.getStringExtra("name");




    }

    //creating a hyperlink to link to external website
    private void setupHyperlink(){
        mLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}