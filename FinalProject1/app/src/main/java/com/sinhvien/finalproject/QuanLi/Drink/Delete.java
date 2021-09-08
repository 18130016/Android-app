package com.sinhvien.finalproject.QuanLi.Drink;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;

public class Delete extends AppCompatActivity {

    ListView lvDisplay;


    private DatabaseReference mDatabase;
    private ArrayList<Drink> listDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
                addDrinktoList();
                setClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setClick(){
        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder del = new AlertDialog.Builder(Delete.this);
                del.setMessage("Are you sure ??").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Drink drink = (Drink) parent.getItemAtPosition(position);
                                mDatabase.child("drink").child(drink.getName()).removeValue();
                                Intent intent = new Intent(Delete.this, Delete.class);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alt = del.create();
                alt.setTitle("Delete");
                alt.show();
            }
        });

    }

    private void addDrinktoList() {
        DrinkAdapter arrayAdapter;
        arrayAdapter = new DrinkAdapter(Delete.this,R.layout.item_of_drink,listDrink);
        lvDisplay.setAdapter(arrayAdapter);
    }
}