package com.sinhvien.finalproject.QuanLi.Drink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sinhvien.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DrinkDetail extends AppCompatActivity {

    TextView txtInfor;
    ImageView ivImage;
    private String DrinkName;

    private DatabaseReference mDatabase;
    private ArrayList<Drink> listDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent getData = getIntent();
        DrinkName = getData.getStringExtra("DrinkName");

        txtInfor = findViewById(R.id.txtInfor);
        ivImage = findViewById(R.id.ivImage);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDrink = new ArrayList<>();
                for(DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    listDrink.add(drink);
                }
                getImage();
                getInfor();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void getImage() {

        for(int i = 0; i< listDrink.size();i++){
            if(listDrink.get(i).getName().equals(DrinkName)){
                Picasso.get().load(listDrink.get(i).getImage()).into(ivImage);
            }
        }
    }

    public void getInfor() {
        String infor = "";
        for(int i = 0; i< listDrink.size();i++){
            if(listDrink.get(i).getName().equals(DrinkName)){
               infor = "Tên :   "+ listDrink.get(i).getName() + "  Loại :   "+listDrink.get(i).getTypeName()+
                        "\nTiền :   "+ listDrink.get(i).getMoney()+"đ" +"   Điểm :   "+listDrink.get(i).getPoint();
                txtInfor.setText(infor);
            }
        }
    }
}