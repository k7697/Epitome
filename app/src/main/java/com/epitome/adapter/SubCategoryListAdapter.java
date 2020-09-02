package com.epitome.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.epitome.controller.ProductFragment;
import com.epitome.R;
import com.epitome.model.Category;

import java.util.List;

public class SubCategoryListAdapter extends RecyclerView.Adapter<SubCategoryListAdapter.MyViewHolder> {


    List<Category> subCategoryList;

    Context context;

    public SubCategoryListAdapter(Context context, List<Category> subCategories) {
        this.subCategoryList = subCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.subcategory_row, parent, false);
        SubCategoryListAdapter.MyViewHolder viewHolder = new SubCategoryListAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.textView.setText(subCategoryList.get(position).getCategoryName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                ProductFragment productFragment = new ProductFragment();
                Bundle args = new Bundle();
                args.putString("CategoryID",  (subCategoryList.get(position).getCategoryId()));
                productFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, productFragment).addToBackStack(null).commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout subCategoryRow;
        TextView textView;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.subcategory_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
