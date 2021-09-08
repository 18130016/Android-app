package com.sinhvien.finalproject.QuanLi.Department;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class Add extends AppCompatActivity {
    EditText edName;
    Button btnAdd;
    private DatabaseReference dDatabase;
    private ArrayList<Department> listDep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_dep_add);
        edName = findViewById(R.id.edName);
        btnAdd = findViewById(R.id.btnAdd);



        dDatabase = FirebaseDatabase.getInstance().getReference();
        dDatabase.child("department").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDep = new ArrayList<>();
                for(DataSnapshot dp:snapshot.getChildren()){
                    Department d = dp.getValue(Department.class);
                    listDep.add(d);
                }

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Department department = new Department(edName.getText().toString(),edName.getText().toString());

                        boolean c = false;
                        for(Department d:listDep){
                            if(d.getName().equals(department.getName())){
                                c = true;
                                Toast.makeText(Add.this,"Department early exist",Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        if(!c){
                            dDatabase.child("department").child(department.getName()).setValue(department);
                            Toast.makeText(Add.this, "Add success: " + department.getName(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Add.this,List.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}