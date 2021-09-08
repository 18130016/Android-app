package com.sinhvien.finalproject.QuanLi.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sinhvien.finalproject.Login;
import com.sinhvien.finalproject.QuanLi.Department.Department;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {
    EditText edUser,edPass,edName,edPhone,edAddress;
    Button btnAdd;
    Spinner spDep;

    public DatabaseReference mDatabase;
    public DatabaseReference dDatabase;
    private ArrayList<User> listUser = null;
    private ArrayList<String> listDep = null;
    private ArrayAdapter adapterDep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_user_register);

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        edName = findViewById(R.id.edName);
        edPhone = findViewById(R.id.edPhone);
        edAddress = findViewById(R.id.edAddress);
        spDep = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btnAdd);



        dDatabase = FirebaseDatabase.getInstance().getReference();
        dDatabase.child("department").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDep = new ArrayList<>();
                for(DataSnapshot dt:snapshot.getChildren()){
                    Department d = dt.getValue(Department.class);
                    listDep.add(d.getName());
                }
                adapterDep = new ArrayAdapter(Register.this, android.R.layout.simple_spinner_item,listDep);
                adapterDep.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spDep.setAdapter(adapterDep);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Toast.makeText(Register.this,"" + listDep.size(),Toast.LENGTH_SHORT).show();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser = new ArrayList<>();
                for(DataSnapshot d:snapshot.getChildren()){
                    User u = d.getValue(User.class);
                    listUser.add(u);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String users = edUser.getText().toString();
                String pass = edPass.getText().toString();
                if (users.equals("")||pass.equals(""))
                    Toast.makeText(Register.this, "Please fill in username and password", Toast.LENGTH_SHORT).show();
                else {
                    User user = new User(edUser.getText().toString(),
                                    spDep.getSelectedItem().toString(),
                                    edUser.getText().toString(),
                                    edPass.getText().toString(),
                                    edName.getText().toString(),
                                    edPhone.getText().toString(),
                                    edAddress.getText().toString());
                    boolean t = false;
                    for (int i = 0; i < listUser.size(); i++) {
                        if(listUser.get(i).getUser().equals(user.getUser())){
                            Toast.makeText(Register.this,"Similer username try again",Toast.LENGTH_SHORT).show();
                            t = true;
                            break;
                        }

                    }
                    if(!t){
                        Toast.makeText(Register.this, "Success: " + user.toString(), Toast.LENGTH_LONG).show();
                        mDatabase.child("user").child(user.getId()).setValue(user);
                        Intent intent = new Intent(Register.this, com.sinhvien.finalproject.QuanLi.User.List.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });


    }



}