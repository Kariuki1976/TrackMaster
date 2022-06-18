package com.example.phonetracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.phonetracker.R;
import com.example.phonetracker.models.Phone;
import com.example.phonetracker.network.Constants;
import com.example.phonetracker.ui.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

public class FirebasePhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.ProductsImageView)
    ImageView mProductsImageView;
    @BindView(R.id.phoneNameTextView)
    TextView mNameTextView;
    @BindView(R.id.slugTextView) TextView mSlugTextView;
    View mView;
    Context mContext;

    public FirebasePhoneViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPhone(Phone phone) {
        mNameTextView.setText(phone.getPhoneName());
        mSlugTextView.setText( phone.getSlug());
        Picasso.get().load(phone.getImage()).into(mProductsImageView);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Products> smartPhones = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PHONES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    smartPhones.add(snapshot.getValue(Products.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, Products.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("smartPhones", Parcels.wrap(smartPhones));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
