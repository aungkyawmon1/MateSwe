package com.example.mateswe.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateswe.R;
import com.example.mateswe.adapter.CategoryAdapter;
import com.example.mateswe.model.Category;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public CategoryFragment() {
        // Required empty public constructor
    }

    private RecyclerView rcCategory;
    private ArrayList<Category> categoryArrayList;
    private CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        rcCategory = view.findViewById(R.id.rv_category);

        //Get Categories' data
        categoryArrayList = getData();
        categoryAdapter = new CategoryAdapter(getContext(), categoryArrayList);
        rcCategory.setAdapter(categoryAdapter);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcCategory.setLayoutManager(mGridLayoutManager);
        return view;
    }

    private ArrayList<Category> getData(){
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category(1,"စိုက်ပျိုးရေး",R.drawable.agricultural));
        categories.add(new Category(2,"ဝတ္ထု",R.drawable.icon_novel));
        categories.add(new Category(3,"ဟာသ",R.drawable.icon_comedy));
        categories.add(new Category(4,"ရုပ်ပြ",R.drawable.icon_comic));
        categories.add(new Category(5,"ဗဟုသုတ",R.drawable.icon_knowledge));
        categories.add(new Category(6,"ဘာသာစကား",R.drawable.icon_language));
        categories.add(new Category(7,"သမိုင်း",R.drawable.icon_history));
        categories.add(new Category(8,"ဘာသာ",R.drawable.icon_religion));
        return categories;
    }
}
