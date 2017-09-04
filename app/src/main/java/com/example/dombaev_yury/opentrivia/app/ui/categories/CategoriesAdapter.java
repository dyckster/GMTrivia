package com.example.dombaev_yury.opentrivia.app.ui.categories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categories = new ArrayList<>();
    private CategoryClickListener onClickListener;

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindView(categories.get(position), onClickListener);
    }

    public void setItems(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setOnClickListener(CategoryClickListener listener) {
        this.onClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
