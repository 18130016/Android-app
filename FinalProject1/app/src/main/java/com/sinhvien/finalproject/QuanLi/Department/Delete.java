package com.sinhvien.finalproject.QuanLi.Department;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sinhvien.finalproject.QuanLi.User.User;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class Delete extends AppCompatActivity {
    ListView lvDisplay;


    public DatabaseReference mDatabase;
    private ArrayList<Department> listDep = null;
    ArrayAdapter adapterDep = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_dep_delete);

        lvDisplay = findViewById(R.id.lvDisplay);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        listDep = new ArrayList<>();
        adapterDep = new ArrayAdapter<Department>(Delete.this, android.R.layout.simple_list_item_1,listDep);
        lvDisplay.setAdapter(adapterDep);

        mDatabase.child("department").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    Department dp = d.getValue(Department.class);
                    listDep.add(dp);
                    adapterDep.notifyDataSetChanged();
                }
                lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        AlertDialog.Builder del = new AlertDialog.Builder(Delete.this);
                        del.setMessage("Are you sure ??").setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Department department = (Department) parent.getItemAtPosition(position);
                                        mDatabase.child("department").child(department.getName()).removeValue();
                                        Intent intent = new Intent(Delete.this, com.sinhvien.finalproject.QuanLi.Department.Delete.class);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        Department department = (Department) parent.getItemAtPosition(position);
                        AlertDialog alt = del.create();
                        alt.setTitle("Delete Department : " + department.getName());
                        alt.show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}