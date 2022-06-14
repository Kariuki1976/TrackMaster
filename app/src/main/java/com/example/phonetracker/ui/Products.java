package com.example.phonetracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonetracker.network.DialogFragment;
import com.example.phonetracker.models.LatestPhoneSearch;
import com.example.phonetracker.R;
import com.example.phonetracker.adapters.ProductsListAdapter;
import com.example.phonetracker.models.Phone;
import com.example.phonetracker.network.AzharimApi;
import com.example.phonetracker.network.AzharimmClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Products extends AppCompatActivity {
    @BindView(R.id.editTextTextPersonName) EditText mName;
    @BindView(R.id.hyperlinkTextView) TextView mLink;
    @BindView(R.id.contactUs)
    ImageButton mImageButton;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String view = getIntent().getStringExtra("create");
        mName.setText(view);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String view2 = mName.getText().toString();
                Intent intent = new Intent(Products.this, aboutus.class);
                Toast.makeText(Products.this, "Hello"  + " " + view2, Toast.LENGTH_SHORT).show();
                intent.putExtra("create", view2);
                startActivity(intent);
            }
        });
//        api query
        AzharimApi client = AzharimmClient.getClient();

        Boolean status = null;
        Call<LatestPhoneSearch> call = (Call<LatestPhoneSearch>) client.getphonetracker();
        call.enqueue(new Callback<LatestPhoneSearch>() {


            @Override
            public void onResponse(Call<LatestPhoneSearch> call, Response<LatestPhoneSearch> response) {

                if (response.isSuccessful()) {
                    List<Phone> myPhone= response.body().getData().getPhones();
                    ProductsListAdapter mAdapter = new ProductsListAdapter(Products.this, myPhone);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(Products.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                } else {
                    Toast.makeText(Products.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LatestPhoneSearch> call, Throwable t) {
                Toast.makeText(Products.this, "Unexpected error", Toast.LENGTH_SHORT).show();
                Log.e(Products.class.getSimpleName(),"Error",t);
            }

        });



//        getIntent().putExtra("slim", view);


        //adding a builder dialogue
//        FragmentManager fm = getSupportFragmentManager();
//        DialogFragment dialogFragment = new DialogFragment();
//        dialogFragment.show(fm, "Sample Fragment");




        //Fetching user input from the MainActivity
//        Intent intent = new Intent(EditText, );
//        String name = intent.getStringExtra("name");




    }

    //creating a hyperlink to link to external website
    private void setupHyperlink(){
        mLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}