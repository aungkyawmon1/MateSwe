package com.example.mateswe.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mateswe.R;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.User;

public class EditProfileActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    ImageView profile, back;
    EditText userName, password, confirmPassword, phoneNo, address;
    Button save;
    Uri selectedImage;
    CardView cardView;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        linearLayout = findViewById(R.id.ll_post_image);
        profile = findViewById(R.id.iv_profile);
        userName = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        confirmPassword = findViewById(R.id.et_confirm_password);
        phoneNo = findViewById(R.id.et_phone_no);
        address = findViewById(R.id.et_address);
        save = findViewById(R.id.btn_save);
        cardView = findViewById(R.id.card_post);
        back = findViewById(R.id.iv_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        user = AppDatabase.getAppDatabase(EditProfileActivity.this).userDao().getUser(pref.getString("user_name",""),pref.getString("password",""));

        //Set data to text box
        userName.setText(user.getUser_name());
        phoneNo.setText(user.getPhone_no());
        address.setText(user.getAddress());
        if(user.getPhoto() != null ){
            cardView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            Glide.with(getApplicationContext())
                    .load(Uri.parse(user.getPhoto()))
                    .into(profile);
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = userName.getText().toString();
                String strPassword = password.getText().toString();
                String strConfirmPassword = confirmPassword.getText().toString();
                String strPhoneNo = phoneNo.getText().toString();
                String strAddress = address.getText().toString();
                String strPhoto = null;
                if(selectedImage != null){
                    strPhoto = selectedImage.toString();
                }
                if(strPassword.equals(strConfirmPassword)){
                    user.setUser_name(strUserName);
                    user.setPassword(strPassword);
                    user.setPhone_no(strPhoneNo);
                    user.setAddress(strAddress);
                    user.setPhoto(strPhoto);
                    editor.clear();
                    editor.putString("user_name",strUserName);
                    editor.putString("password",strPassword);
                    editor.commit();
                    AppDatabase.getAppDatabase(EditProfileActivity.this).userDao().update(user);
                    Toast.makeText(getApplicationContext(),"Edited!",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password must be same!",Toast.LENGTH_SHORT).show();
                }
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
                    linearLayout.setVisibility(View.GONE);
                    selectedImage = data.getData();
                    Toast.makeText(this,""+selectedImage,Toast.LENGTH_SHORT).show();
                    profile.setImageURI(selectedImage);
                    break;
            }


    }
}
