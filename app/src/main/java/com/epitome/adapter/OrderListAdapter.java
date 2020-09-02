package com.epitome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.epitome.controller.OrderDetail;
import com.epitome.R;
import com.epitome.model.Order;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder>{

    private Context context;
    private List<Order> orderList;

    public OrderListAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.order_card_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.MyViewHolder holder, final int position) {

        holder.statusTV.setText(orderList.get(position).getShippedStatus());
        holder.orderIdTV.setText(String.valueOf( orderList.get(position).getOrderId()));
        holder.dateOrderedTV.setText("Not Available");
        holder.viewDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                OrderDetail orderDetailFragment = new OrderDetail(orderList.get(position));
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, orderDetailFragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView statusTV , orderIdTV, dateOrderedTV, viewDetailsTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            statusTV = itemView.findViewById(R.id.status);
            orderIdTV = itemView.findViewById(R.id.orderId);
            dateOrderedTV = itemView.findViewById(R.id.dateOrdered);
            viewDetailsTV = itemView.findViewById(R.id.viewDetails);

        }
    }
}
