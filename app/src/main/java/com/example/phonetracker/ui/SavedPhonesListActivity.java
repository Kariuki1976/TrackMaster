package com.example.phonetracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phonetracker.R;
import com.example.phonetracker.adapters.FirebasePhoneViewHolder;
import com.example.phonetracker.models.Phone;
import com.example.phonetracker.network.Constants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedPhonesListActivity extends AppCompatActivity {
    private DatabaseReference mPhoneReference;
    private FirebaseRecyclerAdapter<Phone, FirebasePhoneViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
//    @BindView(R.id.progressBar)
//    ProgressBar mProgressBar;
@BindView(R.id.progressbar) ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);

        mPhoneReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PHONES);
        setUpFirebaseAdapter();
        hideProgressBar();
        showRestaurants();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Phone> options =
                new FirebaseRecyclerOptions.Builder<Phone>()
                        .setQuery(mPhoneReference, Phone.class)
                        .build();


        mFirebaseAdapter = new FirebaseRecyclerAdapter<Phone, FirebasePhoneViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebasePhoneViewHolder firebaseRestaurantViewHolder, int position, @NonNull Phone phone) {
                firebaseRestaurantViewHolder.bindPhone(phone);
            }

            @NonNull
            @Override
            public FirebasePhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list_item, parent, false);
                return new FirebasePhoneViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
