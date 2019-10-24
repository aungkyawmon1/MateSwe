package com.example.mateswe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mateswe.R;
import com.example.mateswe.db.AppDatabase;
import com.example.mateswe.entity.User;

public class SignUpActivity extends AppCompatActivity {

    ImageView back;
    EditText userName, password, confirmPassword, phoneNo, address;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        back = findViewById(R.id.iv_back);
        userName = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        confirmPassword = findViewById(R.id.et_confirm_password);
        phoneNo = findViewById(R.id.et_phone_no);
        address = findViewById(R.id.et_address);
        signUp = findViewById(R.id.btn_sign_up);

        //back imageview
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = userName.getText().toString();
                String strPassword = password.getText().toString();
                String strConfirmPassword = confirmPassword.getText().toString();
                String strPhoneNo = phoneNo.getText().toString();
                String strAddress = address.getText().toString();
                if(strPassword.equals(strConfirmPassword)){
                    User user = new User();
                    user.setUser_name(strUserName);
                    user.setPassword(strPassword);
                    user.setPhone_no(strPhoneNo);
                    user.setAddress(strAddress);
                    AppDatabase.getAppDatabase(SignUpActivity.this).userDao().insert(user);
                    Toast.makeText(getApplicationContext(),"Registered!",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password must be same!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
