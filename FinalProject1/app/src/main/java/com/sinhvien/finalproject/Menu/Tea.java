package com.sinhvien.finalproject.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sinhvien.finalproject.NhanVien.Hoadon;
import com.sinhvien.finalproject.NhanVien.ThanhToan;
import com.sinhvien.finalproject.QuanLi.Drink.Drink;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class Tea extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btncoffee2,btnsmoothie2,btnjuice2,btnfood2;
    ImageView imgthanhtoan,imghome;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerViewPopular();

        Button();
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<Drink> foodList =new ArrayList<>();

        //lấy dữ liệu từ database và hiển thị ra màn hình theo loại nước là Tea
        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    if(drink.getTypeName().equals("Tea")){
                        foodList.add(drink);
                    }
                }
                adapter = new PopularAdapter(foodList);
                recyclerViewPopularList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void Button(){
        btncoffee2=(Button)findViewById(R.id.btn_coffee2);
        btncoffee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Tea.this,Coffee.class);
                startActivity(i);
            }
        });
        btnsmoothie2=(Button)findViewById(R.id.btn_smoothie2);
        btnsmoothie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Tea.this,Smoothie.class);
                startActivity(i);
            }
        });
        btnjuice2=(Button)findViewById(R.id.btn_juice2);
        btnjuice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Tea.this,Juice.class);
                startActivity(i);
            }
        });
        btnfood2=(Button)findViewById(R.id.btn_Food2);
        btnfood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Tea.this, Food.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan2);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Tea.this, ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home2);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Tea.this, Hoadon.class);
                startActivity(i);
            }
        });
    }
}