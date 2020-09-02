package com.epitome.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.squareup.picasso.Picasso;
import com.epitome.R;
import com.epitome.api.ApiClient;
import com.epitome.api.ApiInterface;
import com.epitome.response.ProductResponse;
import com.epitome.model.CartProducts;
import com.epitome.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<CartProducts> cartProducts;
    private Product productn;
    ProductResponse product;
    Dialog dialog;
    SharedPreferences pref;
    double total;



    public CartListAdapter(Context context, ArrayList<CartProducts> cartProducts) {
        this.context = context;
        this.cartProducts = cartProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cart_item_card, parent, false);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.edit_cart_item_card);
        Button  saveEdit, cancelEdit;
        saveEdit = (Button) dialog.findViewById(R.id.saveEditCartItem);
        cancelEdit = (Button) dialog.findViewById(R.id.cancelEditCartItem);

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        String unm = "admin";
        String pwd = "1234";
        String base = unm + ":" + pwd;
        String keyHeader = "stylestamp@123";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);




        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductResponse> call;
        call = apiInterface.getProductById(authHeader, keyHeader,"1");//cartProducts.get(position).getProductId());
        Log.e("cart-pro-id",cartProducts.get(position).getProductId());
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Log.e("log",response.body().getMessage());
                if(response.isSuccessful() && response.body() != null ){
                    product = response.body();
//                        int size=product.getProducts().size();
                    //                    Picasso.get().load( product.getImages().get(0).getUrl()).into(holder.productImage);
                    holder.productTitle.setText(String.valueOf(product.getProduct().getProductName()));
                    holder.productPrice.setText(String.valueOf(product.getProduct().getPrice()));
                    holder.productID.setText(String.valueOf(product.getProduct().getProductID()));
                    pref = context.getSharedPreferences("mp", 0);
                    total+=(Double.parseDouble(product.getProduct().getPrice()) *Double.parseDouble(cartProducts.get(position).getQuantity()) /*+Double.parseDouble(pref.getString("total", "0")*/);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("total",String.valueOf(total));
                    editor.apply();
                    Log.e("total", String.valueOf(total));

                }
                else{
                    Toast.makeText(context, "No results", Toast.LENGTH_SHORT).show();
                    holder.productTitle.setText("Not Available");
                    holder.productPrice.setText("Not Available");
                    holder.productID.setText("Not Available");
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

                Log.e("product-api-res-fail",t.getMessage());
            }

        });

        holder.quantity.setText(cartProducts.get(position).getQuantity());
        holder.size.setText(cartProducts.get(position).getSize());
//        holder.size.setText(cartProducts.get(position).getColor());

        holder.removeCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write more code here afterwards *#*#
            }
        });

        holder.editCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView productID, productTitle, productPrice, quantity, size, totalPrice;
        ImageView productImage;

        Button editCartItem, removeCartItem ;
        public MyViewHolder(View itemView) {
            super(itemView);

            productID = (TextView) itemView.findViewById(R.id.productId_cartItem);
            productTitle = (TextView) itemView.findViewById(R.id.title_cartItem);
            productPrice = (TextView) itemView.findViewById(R.id.price_cartItem);
            productImage = (ImageView) itemView.findViewById(R.id.productImage_cartItem);
            quantity = (TextView) itemView.findViewById(R.id.quantity_cartItem);
            size = (TextView) itemView.findViewById(R.id.size_cartItem);
            editCartItem = (Button) itemView.findViewById(R.id.editCartItem);
            removeCartItem = (Button) itemView.findViewById(R.id.removeCartItem);

        }
    }
}
