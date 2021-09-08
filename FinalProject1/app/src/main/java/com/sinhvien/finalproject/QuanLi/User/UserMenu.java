package com.sinhvien.finalproject.QuanLi.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sinhvien.finalproject.R;

public class UserMenu extends AppCompatActivity {

    Button btnList,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_user_menu);

        btnList = findViewById(R.id.btnList);
        btnDelete = findViewById(R.id.btnDelete);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMenu.this,List.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMenu.this,Delete.class);
                startActivity(intent);
            }
        });
    }
}