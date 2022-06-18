package com.example.phonetracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonetracker.network.Constants;
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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private static final String TAG = Products.class.getSimpleName();
    private ProductsListAdapter mAdapter;
    @BindView(R.id.editTextTextPersonName) EditText mName;
    @BindView(R.id.hyperlinkTextView) TextView mLink;
    @BindView(R.id.contactUs)
    ImageButton mImageButton;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;
    public List<Phone> mPhone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        Intent intennt = getIntent();
        String view = getIntent().getStringExtra("create");
//        String view = mRecentAddress;
        mName.setText(view);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_PHONE_KEY, null);
//        Log.d("Shared Pref Location", mRecentAddress);
        if(mRecentAddress != null){
            fetchPhone(mRecentAddress);
        }

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String view2 = mName.getText().toString();
                Intent intenttt = new Intent(Products.this, aboutus.class);
                Toast.makeText(Products.this, "Hello"  + " " + view2, Toast.LENGTH_SHORT).show();
                intenttt.putExtra("create", view2);
                startActivity(intenttt);
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
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(fm, "Sample Fragment");




        //Fetching user input from the MainActivity
//        Intent intent = new Intent(EditText, );
//        String view = intent.getStringExtra("name");




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String phone) {
                addToSharedPreferences(phone);
                fetchPhone(phone);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String location) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void addToSharedPreferences(String phone) {
        mEditor.putString(Constants.PREFERENCES_PHONE_KEY, phone).apply();
    }
    private void fetchPhone(String phone){
        AzharimApi client = AzharimmClient.getClient();
        Call<LatestPhoneSearch> call = client.getphonetracker();
        call.enqueue(new Callback<LatestPhoneSearch>() {
            @Override
            public void onResponse(Call<LatestPhoneSearch> call, Response<LatestPhoneSearch> response) {

                hideProgressBar();

                if (response.isSuccessful()) {
                    mPhone = response.body().getData().getPhones();
                    mAdapter = new ProductsListAdapter(Products.this, mPhone);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Products.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<LatestPhoneSearch> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
    //creating a hyperlink to link to external website
//    private void setupHyperlink(){
//        mLink.setMovementMethod(LinkMovementMethod.getInstance());
//    }
}