package com.epitome.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.epitome.controller.ProductDetail;
import com.epitome.R;
import com.epitome.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> implements Filterable {


    private Context context;
    private List<Product> products;
    private List<Product> arrProductListFiltered = new ArrayList<>();

    public ProductListAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        this.arrProductListFiltered = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.product_card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.productTitle.setText(arrProductListFiltered.get(position).getProductName());
      holder.productPrice.setText(String.valueOf(arrProductListFiltered.get(position).getPrice()));

       // Picasso.get().load( arrProductListFiltered.get(position).getImages().get(0).getUrl()).into(holder.productImage);
        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) context;
                ProductDetail productDetailFragment = new ProductDetail();
                Bundle args = new Bundle();
                args.putString("productID", arrProductListFiltered.get(position).getProductID());
                productDetailFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, productDetailFragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrProductListFiltered.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productTitle , productPrice;
        ImageView productImage;
        CardView productCardView;
        public MyViewHolder(View itemView){
            super(itemView);
            productTitle = (TextView) itemView.findViewById(R.id.productTitle);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            productCardView = (CardView) itemView.findViewById(R.id.productCard);


        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                Log.i("Adapter","character String" + charString);
                if (charString.isEmpty()) {
                    arrProductListFiltered = products;
                } else {
                    ArrayList<Product> filteredList = new ArrayList<>();

                    arrProductListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrProductListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrProductListFiltered = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
