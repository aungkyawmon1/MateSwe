package com.example.mateswe.fragment;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateswe.R;
import com.example.mateswe.adapter.FavouriteBookAdapter;
import com.example.mateswe.model.FavouriteBook;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView rcFavouriteBook, rcMostReadBook, rcBooks;
    private ArrayList<FavouriteBook> alFavouriteBook;
    private FavouriteBookAdapter favouriteBookAdapter, mostReadAdapter, booksAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcFavouriteBook = view.findViewById(R.id.rv_favourite_book);
//        rcMostReadBook = view.findViewById(R.id.rv_most_read_book);
//        rcBooks = view.findViewById(R.id.rv_books);

        //Favourite Book
        alFavouriteBook = getData();
        favouriteBookAdapter = new FavouriteBookAdapter(getContext(),alFavouriteBook);
        rcFavouriteBook.setAdapter(favouriteBookAdapter);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcFavouriteBook.setLayoutManager(mGridLayoutManager);
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

    private ArrayList<FavouriteBook> getData(){
        ArrayList<FavouriteBook> favouriteBooks = new ArrayList<>();

        favouriteBooks.add(new FavouriteBook(R.drawable.alinkaryeekyaw_pandaung,"ပန်းဒေါင်း","အလက်ာရည်ကျော်"));
        favouriteBooks.add(new FavouriteBook(R.drawable.juu_iwanttoseeyou,"တွေ့ချင်လှပြီ","ဂျူး"));
        favouriteBooks.add(new FavouriteBook(R.drawable.juu_maungtoeyoutekyartwe,"မောင်တို့ယောက်ျားတွေ","ဂျူး"));
        favouriteBooks.add(new FavouriteBook(R.drawable.juu_thetownilove,"ကျွန်မချစ်သော မြို့","ဂျူး"));
        favouriteBooks.add(new FavouriteBook(R.drawable.luhtuseinwin_first_sprit,"အထက်တန်စား စိတ်ဓာတ်","လူထုစိန်ဝင်း"));
        return favouriteBooks;
    }
}
