package com.example.phonetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.phonetracker.models.Phone;
import com.example.phonetracker.ui.Products;
import com.example.phonetracker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductsViewHolder> {
    private List<Phone> mPhone;
    private Context mContext;

    public ProductsListAdapter(Context context, List<Phone> phone) {
        mContext = context;
        mPhone = phone;
    }


    @Override
    public ProductsListAdapter.ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list_item, parent, false);
        ProductsViewHolder viewHolder = new ProductsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.bindProducts(mPhone.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhone.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ProductsImageView)
        ImageView mProductsImageView;
        @BindView(R.id.phoneNameTextView)
        TextView mNameTextView;
        @BindView(R.id.slugTextView) TextView mSlugTextView;

        private Context mContext;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindProducts(Phone phone) {
            mNameTextView.setText(phone.getPhoneName());
            mSlugTextView.setText( phone.getSlug());
            Picasso.get().load(phone.getImage()).into(mProductsImageView);
        }
    }
}
