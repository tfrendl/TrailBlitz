package com.example.trailblitz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author Talia Frendl
 * @since December 13, 2023
 *
 * Recycler View:
 * https://www.youtube.com/watch?v=Mc0XT58A1Z4
 */

public class Store_RecyclerViewAdapter extends RecyclerView.Adapter<Store_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<StoreModel> storeModels;
    public Store_RecyclerViewAdapter(Context context, ArrayList<StoreModel> storeModels) {
        this.context = context;
        this.storeModels = storeModels;
    }
    @NonNull
    @Override
    public Store_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (give a look to each of the rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new Store_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Store_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view

        holder.tvItem.setText(storeModels.get(position).getItem());
        holder.tvPrice.setText("$" + String.valueOf(storeModels.get(position).getPrice()));
        holder.tvQuantity.setText(String.valueOf(storeModels.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed
        return storeModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // grabbing the views from the recycler_view_row layout file
        // kinda like the onCreate method

        TextView tvItem;
        TextView tvPrice;
        TextView tvQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.textView);
            tvPrice = itemView.findViewById(R.id.textView3);
            tvQuantity = itemView.findViewById(R.id.textView4);
        }
    }
}
