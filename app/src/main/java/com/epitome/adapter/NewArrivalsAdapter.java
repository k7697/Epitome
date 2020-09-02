package com.epitome.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.epitome.controller.ProductDetail;
import com.epitome.R;
import com.epitome.model.Product;

import java.util.List;

public class NewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalsAdapter.MyViewHolder> {


    private Context context;
    private List<Product> products;

    public NewArrivalsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public NewArrivalsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.new_arrivals_related_products_card, parent, false);
        return new NewArrivalsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalsAdapter.MyViewHolder holder, final int position) {
// productResponse class only? no product list adapter
        holder.productTitle.setText(products.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(products.get(position).getPrice()));
        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                ProductDetail productDetailFragment = new ProductDetail();
                Bundle args = new Bundle();
                args.putString("productID", products.get(position).getProductID());
                productDetailFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, productDetailFragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (products.size() <= 10) {
            return products.size();
        } else {
            return 10;
        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productTitle, productPrice;
        ImageView productImage;
        CardView productCardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            productTitle = (TextView) itemView.findViewById(R.id.productTitle_newAV);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice_newAV);
            productImage = (ImageView) itemView.findViewById(R.id.productImage_newAV);
            productCardView = (CardView) itemView.findViewById(R.id.productCard_newAV);


        }
    }


}