package com.example.phonetracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonetracker.R;
import com.example.phonetracker.network.Constants;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import butterknife.BindView;
import butterknife.ButterKnife;

//to use shared preference add implements view.OnClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedPhonesReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ValueEventListener mSearchedPhonesReferenceListener;

//    @BindView(R.id.EditText)
//    EditText mLocationEditText;

    @BindView(R.id.submitButton)
    Button mButton;
    @BindView(R.id.savedPhoneButton) Button mSavedPhoneButton;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.EditText)
    TextInputLayout mtextInputEditText;


    boolean isAllFieldsChecked = true;
//    private DatagramSocket ButterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedPhonesReference = FirebaseDatabase
                .getInstance()
                .getReference()
//                pinpoint phone node
                .child(Constants.FIREBASE_CHILD_SEARCHED_PHONES);


        mSearchedPhonesReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot phoneSnapshot : dataSnapshot.getChildren()) {
                    String phone = phoneSnapshot.getValue().toString();
                    Log.d("Phone search updated", "phone type: " + phone); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
                //display welcome message
            }
        };

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
//here
        mButton.setOnClickListener((View.OnClickListener) this);


        mButton.setOnClickListener((View.OnClickListener) this);
        mSavedPhoneButton.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
        public void onClick (View v){
            if (v == mButton) {
                String phone = mtextInputEditText.getEditText().getText().toString();
                addToSharedPreferences(phone);
                Intent intentt = new Intent(MainActivity.this, Products.class);
                startActivity(intentt);
            }
            if (v == mButton) {
                String view = mtextInputEditText.getEditText().getText().toString();

                saveViewToFirebase(view);

                Intent intent = new Intent(MainActivity.this, Products.class);
                Toast.makeText(MainActivity.this, "Hello" + " " + view, Toast.LENGTH_SHORT).show();
                intent.putExtra("create", view);
                startActivity(intent);


            }

            if (v == mSavedPhoneButton) {
                Intent intent = new Intent(MainActivity.this, SavedPhonesListActivity.class);
                startActivity(intent);
            }
        }



//    public void saveViewToFirebase(String view) {
//        mSearchedPhonesReference.setValue(view);
//    }
    public void saveViewToFirebase(String view) {
        mSearchedPhonesReference.push().setValue(view);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedPhonesReference.removeEventListener(mSearchedPhonesReferenceListener);
    }

//shared preference

    private void addToSharedPreferences(String phones) {
        mEditor.putString(Constants.PREFERENCES_PHONE_KEY, phones).apply();
    }
}
