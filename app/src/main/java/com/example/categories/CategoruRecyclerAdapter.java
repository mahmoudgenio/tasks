package com.example.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.R;
import com.example.tables.Category;

import java.util.List;


public class CategoruRecyclerAdapter extends RecyclerView.Adapter<CategoruRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Category> categories;

    // Constructor
    public CategoruRecyclerAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textDescription, textQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            //textName = itemView.findViewById(R.id.textName);
            textDescription = itemView.findViewById(R.id.textDescription);
            textQuantity = itemView.findViewById(R.id.textQuantity);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);

        // Bind data to views
       // holder.textName.setText(category.getName());
        holder.textDescription.setText(category.getDesc());
        holder.textQuantity.setText(String.valueOf(category.getQhs()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}