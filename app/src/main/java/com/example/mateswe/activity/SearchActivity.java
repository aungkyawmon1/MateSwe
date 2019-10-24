package com.example.mateswe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.mateswe.R;
import com.example.mateswe.adapter.FavouriteBookAdapter;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.Book;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    MaterialSearchBar searchBar;

    private RecyclerView rcFavouriteBook;
    private List<Book> alFavouriteBook;
    private FavouriteBookAdapter favouriteBookAdapter;
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rcFavouriteBook = findViewById(R.id.rv_favourite_book);
        searchBar = findViewById(R.id.searchBar);
        searchBar.enableSearch();

        alFavouriteBook = AppDatabase.getAppDatabase(getApplicationContext()).bookDao().getAll();

        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                book = new Book();
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
                alFavouriteBook.clear();
                alFavouriteBook = AppDatabase.getAppDatabase(getApplicationContext()).bookDao().searchAuthor("%"+searchBar.getText().toString()+"%");
                favouriteBookAdapter = new FavouriteBookAdapter(getApplicationContext(),alFavouriteBook);
                rcFavouriteBook.setAdapter(favouriteBookAdapter);
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                rcFavouriteBook.setLayoutManager(mGridLayoutManager);
                favouriteBookAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
    }
}
