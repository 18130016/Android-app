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

public class Juice extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btncoffee3,btntea3,btnsmoothie3,btnfood3;
    ImageView imgthanhtoan,imghome;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juice);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerViewPopular();
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView3);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<Drink> foodList =new ArrayList<>();

        //lấy dữ liệu từ database và hiển thị ra màn hình theo loại nước là juice
        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    if(drink.getTypeName().equals("Juice")){
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
        btncoffee3=(Button)findViewById(R.id.btn_coffee3);
        btncoffee3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Juice.this,Coffee.class);
                startActivity(i);
            }
        });
        btntea3=(Button)findViewById(R.id.btn_tea3);
        btntea3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Juice.this,Tea.class);
                startActivity(i);
            }
        });
        btnsmoothie3=(Button)findViewById(R.id.btn_smoothie3);
        btnsmoothie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Juice.this,Smoothie.class);
                startActivity(i);
            }
        });
        btnfood3=(Button)findViewById(R.id.btn_Food3);
        btnfood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Juice.this, Food.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan3);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Juice.this, ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home3);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Juice.this, Hoadon.class);
                startActivity(i);
            }
        });
    }
}