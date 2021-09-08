package com.sinhvien.finalproject.QuanLi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sinhvien.finalproject.Login;
import com.sinhvien.finalproject.QuanLi.Department.DepMenu;
import com.sinhvien.finalproject.QuanLi.Drink.DrinkMenu;
import com.sinhvien.finalproject.QuanLi.User.UserMenu;
import com.sinhvien.finalproject.R;

public class QuanLiNhanVien extends AppCompatActivity {

    Button btnUser,btnDep,btnDrink,btnLogout;
    TextView txtAdmin;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_nhan_vien);
        btnUser = findViewById(R.id.btnUser);
        btnDep = findViewById(R.id.btnDep);

        btnDrink = findViewById(R.id.btnDrink);
        btnLogout = findViewById(R.id.btnLogout);
        txtAdmin = findViewById(R.id.txtAdmin);

        Intent getData = getIntent();
        user = getData.getStringExtra("Username");

        txtAdmin.setText("Welcome "+user);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLiNhanVien.this, UserMenu.class);
                startActivity(intent);
            }
        });
        btnDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLiNhanVien.this, DepMenu.class);
                startActivity(intent);
            }
        });
        btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLiNhanVien.this, DrinkMenu.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLiNhanVien.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}