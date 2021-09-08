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
import android.widget.Toast;

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

public class Food extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    Button btncoffee5,btntea5,btnsmoothie5,btnjuice5;
    ImageView imgthanhtoan,imghome;
    HorizontalScrollView scroll;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button();
        scroll = (HorizontalScrollView) findViewById(R.id.foodscroll);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerViewPopular();

    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewPopularList =findViewById(R.id.recyclerView5);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<Drink> foodList =new ArrayList<>();

        //lấy dữ liệu từ database và hiển thị ra màn hình theo loại nước là food
        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    if(drink.getTypeName().equals("Food")){
                        foodList.add(drink);

                    }
                }
                scroll.scrollTo(scroll.getMaxScrollAmount(),0);
                adapter = new PopularAdapter(foodList);
                recyclerViewPopularList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void Button(){
        btncoffee5=(Button)findViewById(R.id.btn_coffee5);
        btncoffee5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Coffee.class);
                startActivity(i);
            }
        });
        btntea5=(Button)findViewById(R.id.btn_tea5);
        btntea5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Tea.class);
                startActivity(i);
            }
        });
        btnsmoothie5=(Button)findViewById(R.id.btn_smoothie5);
        btnsmoothie5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Smoothie.class);
                startActivity(i);
            }
        });
        btnjuice5=(Button)findViewById(R.id.btn_juice5);
        btnjuice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Food.this, Juice.class);
                startActivity(i);
            }
        });

        imgthanhtoan=(ImageView)findViewById(R.id.img_thanhtoan5);
        imgthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Food.this, ThanhToan.class);
                startActivity(i);
            }
        });

        imghome=(ImageView)findViewById(R.id.img_home5);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Food.this, Hoadon.class);
                startActivity(i);
            }
        });
    }
}