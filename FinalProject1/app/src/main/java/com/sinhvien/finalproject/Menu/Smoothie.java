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
import android.widget.HorizontalScrollView;
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

public class Smoothie extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btncoffee4,btntea4,btnjuice4,btnfood4;
    ImageView imgthanhtoan,imghome;
    HorizontalScrollView smoothie_scroll;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoothie);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button();
        smoothie_scroll = (HorizontalScrollView)findViewById(R.id.smoothie_scroll);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerViewPopular();
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView4);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<Drink> foodList =new ArrayList<>();

        //lấy dữ liệu từ database và hiển thị ra màn hình theo loại nước là Smoothie
        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    if(drink.getTypeName().equals("Smoothie")){
                        foodList.add(drink);
                    }
                }
                smoothie_scroll.scrollTo(smoothie_scroll.getMaxScrollAmount(),0);
                adapter = new PopularAdapter(foodList);
                recyclerViewPopularList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void Button(){
        btncoffee4=(Button)findViewById(R.id.btn_coffee4);
        btncoffee4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this,Coffee.class);
                startActivity(i);
            }
        });
        btntea4=(Button)findViewById(R.id.btn_tea4);
        btntea4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this,Tea.class);
                startActivity(i);
            }
        });
        btnjuice4=(Button)findViewById(R.id.btn_juice4);
        btnjuice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this,Juice.class);
                startActivity(i);
            }
        });
        btnfood4=(Button)findViewById(R.id.btn_Food4);
        btnfood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Smoothie.this, Food.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan4);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Smoothie.this, ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home4);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Smoothie.this, Hoadon.class);
                startActivity(i);
            }
        });

    }
}