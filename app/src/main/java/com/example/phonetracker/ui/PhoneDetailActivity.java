package com.example.phonetracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonetracker.R;
import com.example.phonetracker.models.Data;
import com.example.phonetracker.network.AzharimApi;
import com.example.phonetracker.network.AzharimmClient;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneDetailActivity extends AppCompatActivity {
    @BindView(R.id.phoneDetailImage)
    ImageView mPhoneDetail;
    @BindView(R.id.releaseDateTextView)
    TextView mReleaseDate;
    @BindView(R.id.osTextView) TextView mOs;
    @BindView(R.id.dimensionTextView) TextView mDimension;
    @BindView(R.id.storageTextView)TextView mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_detail);
        ButterKnife.bind(this);
        String phoneSlug = getIntent().getStringExtra("slug");
        getphonedetails(phoneSlug);

    }
    private void getphonedetails(String slug){
        AzharimApi client = AzharimmClient.getClient();
        Call<Data> call = client.getphonedetails(slug);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.isSuccessful()){
                    Data data = response.body();
                    Picasso.get().load(data.getThumbnail()).into(mPhoneDetail);
                   mReleaseDate.setText(data.getReleaseDate());
                   mDimension.setText(data.getDimension());
                   mOs.setText(data.getOs());
                   mStorage.setText(data.getStorage());

                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("PhoneDetailActivity", "Unexpected error: ", t);
            }
        });

    }
}