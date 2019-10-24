package com.example.mateswe.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    String id;
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


        id = getIntent().getExtras().getString("id");

        book = AppDatabase.getAppDatabase(BookActivity.this).bookDao().findByID(id);
        if(book.getPhoto()!= null) {
            Glide.with(this)
                    .load(Uri.parse(book.getPhoto()))
                    .into(bookPhoto);
        }
        bookName.setText(book.getBook_name());
        author.setText(book.getAuthor());
        price.setText(book.getPrice());
        summary.setText(book.getSummary());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(BookActivity.this, EditActivity.class);
                I.putExtra("id" , id);
                startActivity(I);

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookActivity.this);
                alertDialogBuilder.setMessage("Are you sure, you want to delete");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        AppDatabase.getAppDatabase(BookActivity.this).bookDao().delete(book);
                                        finish();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onResume() { // 0 - for private mode
        book = AppDatabase.getAppDatabase(BookActivity.this).bookDao().findByID(id);
        if(book.getPhoto()!= null) {
            Glide.with(this)
                    .load(Uri.parse(book.getPhoto()))
                    .into(bookPhoto);
        }
        bookName.setText(book.getBook_name());
        author.setText(book.getAuthor());
        price.setText(book.getPrice());
        summary.setText(book.getSummary());
        super.onResume();
    }
}
