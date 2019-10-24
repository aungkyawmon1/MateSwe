package com.example.mateswe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mateswe.R;

public class BookActivity extends AppCompatActivity {

    private ImageView back;
    private Button edit, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        back = findViewById(R.id.iv_back);
        edit = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btn_delete);

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
