package com.sinhvien.finalproject.QuanLi.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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



    public DatabaseReference mDatabase;
    private ArrayList<User> listUser = null;
    ArrayAdapter adapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_user_list);
        btnAdd = findViewById(R.id.btnAdd);
        lvDisplay = findViewById(R.id.lvDisplay);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        listUser = new ArrayList<>();
        adapter = new ArrayAdapter<User>(List.this, android.R.layout.simple_list_item_1,listUser);
        lvDisplay.setAdapter(adapter);


        mDatabase.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    User u = d.getValue(User.class);
                    listUser.add(u);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(List.this,""+listUser.get(position).getUser(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(List.this,UserDetail.class);
                intent.putExtra("userName",listUser.get(position).getUser());
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

}