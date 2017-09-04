package com.example.dombaev_yury.opentrivia.app.ui.categories;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.model.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView titleText;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        titleText = itemView.findViewById(R.id.category_title);
    }

    public void bindView(final Category category, final CategoryClickListener onClickListener) {
        titleText.setText(category.getName());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(getAdapterPosition());
            }
        });
    }
}
