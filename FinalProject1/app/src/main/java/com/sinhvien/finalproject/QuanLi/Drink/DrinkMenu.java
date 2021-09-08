package com.sinhvien.finalproject.QuanLi.Drink;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sinhvien.finalproject.R;

public class DrinkMenu extends AppCompatActivity {

    Button btnList,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnList= findViewById(R.id.btnList);
        btnDelete = findViewById(R.id.btnDelete);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinkMenu.this,List.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinkMenu.this,Delete.class);
                startActivity(intent);
            }
        });
    }
}