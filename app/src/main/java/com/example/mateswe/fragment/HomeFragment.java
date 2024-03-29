package com.example.mateswe.fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mateswe.R;
import com.example.mateswe.activity.SearchActivity;
import com.example.mateswe.adapter.FavouriteBookAdapter;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.Book;
import com.example.mateswe.model.FavouriteBook;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public HomeFragment() {
        // Required empty public constructor
    }

    ImageView search;
    private RecyclerView rcFavouriteBook;
    private List<Book> alFavouriteBook;
    private FavouriteBookAdapter favouriteBookAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcFavouriteBook = view.findViewById(R.id.rv_favourite_book);
        search =view.findViewById(R.id.imageSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SearchActivity.class);
                startActivity(i);
            }
        });
//        rcMostReadBook = view.findViewById(R.id.rv_most_read_book);
//        rcBooks = view.findViewById(R.id.rv_books);


        alFavouriteBook = AppDatabase.getAppDatabase(getContext()).bookDao().getAll();
//
//        //Most read book
//        mostReadAdapter = new FavouriteBookAdapter(getContext(), alFavouriteBook);
//        rcMostReadBook.setAdapter(mostReadAdapter);
//        rcMostReadBook.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//
//        //Books
//        booksAdapter = new FavouriteBookAdapter(getContext(), alFavouriteBook);
//        rcBooks.setAdapter(booksAdapter);
//        rcBooks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }
    @Override
    public void onResume() { // 0 - for private mode
        alFavouriteBook.clear();
        alFavouriteBook = AppDatabase.getAppDatabase(getContext()).bookDao().getAll();
        favouriteBookAdapter = new FavouriteBookAdapter(getContext(),alFavouriteBook);
        rcFavouriteBook.setAdapter(favouriteBookAdapter);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcFavouriteBook.setLayoutManager(mGridLayoutManager);
        favouriteBookAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
