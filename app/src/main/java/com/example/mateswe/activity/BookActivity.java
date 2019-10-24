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
    private TextView bookName, author, price,summary;

    AlertDialog.Builder builder;
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
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder = new AlertDialog.Builder(BookActivity.this);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to delete this book ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AppDatabase.getAppDatabase(BookActivity.this).bookDao().delete(book);
                                finish();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Delete!");
                alert.show();
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
