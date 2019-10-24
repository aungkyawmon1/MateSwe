package com.example.mateswe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateswe.R;
import com.example.mateswe.db.AppDatabase;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView register;
    EditText userName, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.tv_register);
        userName = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intObj = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intObj);
            }
        });

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

       // Toast.makeText(getApplicationContext(),"1"+pref.getString("user_name",""),Toast.LENGTH_LONG).show();
        int permission = AppDatabase.getAppDatabase(LoginActivity.this).userDao().permit(pref.getString("user_name",""),pref.getString("password",""));
        if(permission>0){
            Intent intObj = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intObj);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = userName.getText().toString();
                String strPassword = password.getText().toString();
                int permission = AppDatabase.getAppDatabase(LoginActivity.this).userDao().permit(strUserName,strPassword);
                if(permission>0){
                    editor.putString("user_name",strUserName);
                    editor.putString("password",strPassword);
                    editor.commit();
                    Intent intObj = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intObj);
                    finish();

                }

            }
        });







    }
}
