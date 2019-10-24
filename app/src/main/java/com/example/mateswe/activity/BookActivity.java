package com.example.mateswe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mateswe.R;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.Book;

public class BookActivity extends AppCompatActivity {

    private ImageView back, bookPhoto;
    private Button edit, delete;
    private TextView bookName, author, price, summary;
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookPhoto = findViewById(R.id.iv_book_photo);
        back = findViewById(R.id.iv_back);
        edit = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btn_delete);
        bookName = findViewById(R.id.tv_book_name);
        author = findViewById(R.id.tv_author);
        price = findViewById(R.id.tv_price);
        summary = findViewById(R.id.tv_summary);


        String id = getIntent().getExtras().getString("id");

        Toast.makeText(getApplicationContext(),"Password must be same!"+id,Toast.LENGTH_SHORT).show();

        book = AppDatabase.getAppDatabase(BookActivity.this).bookDao().findByID(id);
//        Glide.with(this)
//                .load(Uri.parse(book.getPhoto()))
//                .into(bookPhoto);
//        bookName.setText(book.getBook_name());
//        author.setText(book.getAuthor());
//        price.setText(book.getPrice());
//        summary.setText(book.getSummary());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LoginActivity.class);
                I.putExtra("id" , "1");
            }
        });
    }
}
