package com.example.mateswe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mateswe.R;
import com.example.mateswe.model.Category;
import com.example.mateswe.model.FavouriteBook;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<Category> categoriesArrayList;

    public CategoryAdapter(Context cxt, ArrayList<Category> categoriesArrayList){
        inflater = LayoutInflater.from(cxt);
        this.categoriesArrayList = categoriesArrayList;
    }


    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.category_design, parent, false);
        CategoryAdapter.MyViewHolder holder = new CategoryAdapter.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {

        holder.categoryIcon.setImageResource(categoriesArrayList.get(position).getCategoryIcon());
        holder.categoryName.setText(categoriesArrayList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        ImageView categoryIcon;

        public MyViewHolder(View itemView) {
            super(itemView);

            categoryName = (TextView) itemView.findViewById(R.id.tv_category_name);
            categoryIcon = (ImageView) itemView.findViewById(R.id.iv_category_icon);
        }

    }
}
