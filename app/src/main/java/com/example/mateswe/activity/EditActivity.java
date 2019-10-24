package com.example.mateswe.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mateswe.R;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.Book;

public class EditActivity extends AppCompatActivity {

    String id;
    Book book;

    private LinearLayout photoAdd;
    private ImageView addPhoto;
    private Uri selectedImage;
    private Button post;
    private CardView cardView;
    private EditText bookName, author, price, summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        cardView = findViewById(R.id.card_post);
        photoAdd = findViewById(R.id.ll_post_image);
        addPhoto = findViewById(R.id.iv_post_image);
        bookName = findViewById(R.id.et_book_name);
        author = findViewById(R.id.et_author);
        price = findViewById(R.id.et_price);
        summary = findViewById(R.id.et_summary);
        post = findViewById(R.id.btn_post);

        id = getIntent().getExtras().getString("id");

        book = AppDatabase.getAppDatabase(EditActivity.this).bookDao().findByID(id);
        if(book.getPhoto()!= null){
            cardView.setVisibility(View.VISIBLE);
            photoAdd.setVisibility(View.GONE);
            Glide.with(this)
                    .load(Uri.parse(book.getPhoto()))
                    .into(addPhoto);
        }
        bookName.setText(book.getBook_name());
        author.setText(book.getAuthor());
        price.setText(book.getPrice());
        summary.setText(book.getSummary());



        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strBookName = bookName.getText().toString();
                String strAuthor = author.getText().toString();
                String strPrice = price.getText().toString();
                String strSummary = summary.getText().toString();
                String strPhoto = null;
                if(selectedImage != null){
                    strPhoto = selectedImage.toString();
                }
                Book book= new Book();
                book.setId(Integer.parseInt(id));
                book.setBook_name(strBookName);
                book.setAuthor(strAuthor);
                book.setPrice(strPrice);
                book.setSummary(strSummary);
                book.setPhoto(strPhoto);
                AppDatabase.getAppDatabase(getApplicationContext()).bookDao().update(book);
                Toast.makeText(getApplicationContext(),"Posted!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        photoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });

        addPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pickFromGallery();
            }
        });


    }

    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, 11);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 11:
                    //data.getData returns the content URI for the selected Image
                    cardView.setVisibility(View.VISIBLE);
                    photoAdd.setVisibility(View.GONE);
                    selectedImage = data.getData();
                    addPhoto.setImageURI(selectedImage);
                    break;
            }

    }
}
