package com.example.mateswe.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mateswe.R;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.Book;


public class SavedFragment extends Fragment {


    private LinearLayout photoAdd;
    private ImageView addPhoto;
    private Uri selectedImage;
    private Button post;
    private CardView cardView;
    private EditText bookName, author, price, summary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        cardView = view.findViewById(R.id.card_post);
        photoAdd = view.findViewById(R.id.ll_post_image);
        addPhoto = view.findViewById(R.id.iv_post_image);
        bookName = view.findViewById(R.id.et_book_name);
        author = view.findViewById(R.id.et_author);
        price = view.findViewById(R.id.et_price);
        summary =view.findViewById(R.id.et_summary);
        post = view.findViewById(R.id.btn_post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strBookName = bookName.getText().toString();
                String strAuthor = author.getText().toString();
                String price = author.getText().toString();
                String strSummary = summary.getText().toString();
                String strPhoto = null;
                if(selectedImage != null){
                   strPhoto = selectedImage.toString();
                }
                Book book= new Book();
                book.setBook_name(strBookName);
                book.setAuthor(strAuthor);
                book.setPrice(price);
                book.setSummary(strSummary);
                book.setPhoto(strPhoto);
                AppDatabase.getAppDatabase(getContext()).bookDao().insert(book);
                Toast.makeText(getContext(),"Posted!",Toast.LENGTH_SHORT).show();
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
        return view;
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        Log.e("eeee", "onActivityResult: " );
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
