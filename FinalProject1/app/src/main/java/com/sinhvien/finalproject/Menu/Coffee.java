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

public class Coffee extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btntea,btnsmoothie,btnjuice,btnfood;
    ImageView imgthanhtoan,imghome;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerViewPopular();
        Button();
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<Drink> foodList =new ArrayList<>();

        //lấy dữ liệu từ database và hiển thị ra màn hình theo loại nước là coffee
        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    if(drink.getTypeName().equals("Coffee")){
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
        btntea=(Button)findViewById(R.id.btn_tea);
        btntea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Tea.class);
                startActivity(i);
            }
        });
        btnsmoothie=(Button)findViewById(R.id.btn_smoothie);
        btnsmoothie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Smoothie.class);
                startActivity(i);
            }
        });
        btnjuice=(Button)findViewById(R.id.btn_juice);
        btnjuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Juice.class);
                startActivity(i);
            }
        });
        btnfood=(Button)findViewById(R.id.btn_Food);
        btnfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this, Food.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Coffee.this,Hoadon.class);
                startActivity(i);
            }
        });

    }
}