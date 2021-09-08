package com.sinhvien.finalproject.QuanLi.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class UserDetail extends AppCompatActivity {
    TextView txtInfor;

    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_user_detail);

        txtInfor = findViewById(R.id.txtInfor);

        Intent getData = getIntent();
        String userName = getData.getStringExtra("userName");


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot d:snapshot.getChildren()){
                    User u = d.getValue(User.class);
                    if(u.getUser().equals(userName)){

                        String infor = "Id :  "+u.getId()+"\nDepartment :  "+u.getDepName()+"\nUsername :  "+u.getUser()+"\nPassword :  "
                                +u.getPass()+"\nTên :  "+u.getName()+"\nSố Điện thoại :  "+u.getPhone_Number()+"\nĐịa Chỉ :  "+u.getAddress();
                        txtInfor.setText(infor);
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}