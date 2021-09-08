package com.sinhvien.finalproject.QuanLi.Drink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    Button btnAdd;
    ListView lvDisplay;

    private DatabaseReference mDatabase;
    private ArrayList<Drink> listDrink;
    DrinkAdapter adapterDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnAdd = findViewById(R.id.btnAdd);
        lvDisplay = findViewById(R.id.lvDisplay);

        listDrink = new ArrayList<Drink>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    listDrink.add(drink);
                }
                adapterDrink = new DrinkAdapter(List.this,R.layout.item_of_drink,listDrink);
                lvDisplay.setAdapter(adapterDrink);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List.this,Add.class);
                startActivity(intent);
                finish();
            }
        });
        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(List.this,listDrink.get(position).getName(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(List.this,DrinkDetail.class);
                    intent.putExtra("DrinkName",listDrink.get(position).getName());
                    startActivity(intent);
            }
        });
    }


}