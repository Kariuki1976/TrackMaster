package com.example.phonetracker.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class ProductsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mPhones;

    public ProductsArrayAdapter(Context mContext,int resource,  String[] mPhones) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mPhones = mPhones;
    }

    @Override
    public Object getItem(int position){
        String phone = mPhones[position];

        return String.format(phone+ "\n%s Is an awesome Device. \nOpen the Link below to Products Below: ", phone);
    }

    @Override
    public int getCount(){
        return mPhones.length;
    }


}
