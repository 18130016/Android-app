package com.sinhvien.finalproject.QuanLi.Department;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

    private DatabaseReference dDatabase;
    private ArrayAdapter adapterDep;
    private ArrayList<String> listDep = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_dep_list);
        btnAdd = findViewById(R.id.btnAdd);
        lvDisplay = findViewById(R.id.lvDisplay);

        dDatabase = FirebaseDatabase.getInstance().getReference();
        listDep = new ArrayList<>();
        adapterDep =  new ArrayAdapter(List.this, android.R.layout.simple_list_item_1,listDep);
        lvDisplay.setAdapter(adapterDep);

        dDatabase.child("department").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    Department dp = d.getValue(Department.class);
                    listDep.add(dp.getName());
                    adapterDep.notifyDataSetChanged();
                }
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
    }

}