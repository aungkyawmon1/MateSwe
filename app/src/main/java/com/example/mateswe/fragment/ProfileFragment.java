package com.example.mateswe.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mateswe.R;
import com.example.mateswe.activity.EditProfileActivity;
import com.example.mateswe.activity.LoginActivity;
import com.example.mateswe.activity.MainActivity;
import com.example.mateswe.activity.SignUpActivity;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.User;

import java.io.File;


public class ProfileFragment extends Fragment {


    TextView userName, phoneNo, address, edit;
    Button logOut;
    ImageView profile;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        edit = view.findViewById(R.id.tv_edit);
        userName = view.findViewById(R.id.tv_user_name);
        phoneNo = view.findViewById(R.id.tv_phone_no);
        address = view.findViewById(R.id.tv_address);
        logOut = view.findViewById(R.id.btn_logout);
        profile = view.findViewById(R.id.iv_profile);

        pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        user = AppDatabase.getAppDatabase(getContext()).userDao().getUser(pref.getString("user_name",""),pref.getString("password",""));

        userName.setText(user.getUser_name());
        phoneNo.setText(user.getPhone_no());
        address.setText(user.getAddress());

        if(user.getPhoto() != null)
            Glide.with(getContext())
            .load(Uri.parse(user.getPhoto()))
            .into(profile);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intObj = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intObj);
            }
        });

        //LOGOUT BUTTON
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Intent intObj = new Intent(getContext(), LoginActivity.class);
                startActivity(intObj);
                getActivity().finish();
            }
        });



        return view;
    }
    @Override
    public void onResume() { // 0 - for private mode
        user = AppDatabase.getAppDatabase(getContext()).userDao().getUser(pref.getString("user_name",""),pref.getString("password",""));
        userName.setText(user.getUser_name());
        phoneNo.setText(user.getPhone_no());
        address.setText(user.getAddress());
        if(user.getPhoto() != null)
            Glide.with(getContext())
                    .load(Uri.parse(user.getPhoto()))
                    .into(profile);
        super.onResume();
    }

}
