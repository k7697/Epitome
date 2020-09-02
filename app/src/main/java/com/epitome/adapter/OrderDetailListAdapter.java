package com.epitome.adapter;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.epitome.api.ApiClient;
import com.epitome.api.ApiInterface;
import com.epitome.controller.ProductDetail;
import com.epitome.response.ProductResponse;
import com.epitome.R;
import com.epitome.model.CartProducts;
import com.epitome.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailListAdapter extends RecyclerView.Adapter<OrderDetailListAdapter.MyViewHolder> {


    private Context context;
    private Product product;
    private List<CartProducts> cartProducts;


    public OrderDetailListAdapter(Context context, List<CartProducts> cartProducts) {
        this.context = context;
        this.cartProducts = cartProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.ordered_product_card, parent, false);
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
                    product = response.body().getProduct();

                    //                    Picasso.get().load( product.getImages().get(0).getUrl()).into(holder.productImage);
                    holder.productTitle.setText(String.valueOf(product.getProductName()));
                    holder.productPrice.setText(String.valueOf(product.getPrice()));
                    holder.productID.setText(String.valueOf(product.getProductID()));
                    holder.card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AppCompatActivity activity = (AppCompatActivity) context;
                            ProductDetail productDetailFragment = new ProductDetail(product.getProductID());
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, productDetailFragment).addToBackStack(null).commit();
                        }
                    });


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



    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productID, productTitle, productPrice, quantity, size, totalPrice;
        ImageView productImage;
        CardView card;

        public MyViewHolder(View itemView) {
            super(itemView);
            productID = (TextView) itemView.findViewById(R.id.productId_orderDetail);
            productTitle = (TextView) itemView.findViewById(R.id.title_orderDetail);
            productPrice = (TextView) itemView.findViewById(R.id.price_orderDetail);
            productImage = (ImageView) itemView.findViewById(R.id.productImage_orderDetail);
            quantity = (TextView) itemView.findViewById(R.id.quantity_orderDetail);
            size = (TextView) itemView.findViewById(R.id.size_orderDetail);
            card = (CardView) itemView.findViewById(R.id.orderedProductCard);
        }
    }
}
