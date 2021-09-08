 package com.sinhvien.finalproject.QuanLi.User;

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

import com.sinhvien.finalproject.R;

import java.util.ArrayList;

 public class Delete extends AppCompatActivity {
     ListView lvDisplay;

     public DatabaseReference mDatabase;
     private ArrayList<User> listUser = null;
     ArrayAdapter adapter = null;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         ActionBar actionBar = getSupportActionBar();
         actionBar.hide();
         setContentView(R.layout.activity_user_delete);

         lvDisplay = findViewById(R.id.lvDisplay);
         mDatabase = FirebaseDatabase.getInstance().getReference();

         listUser = new ArrayList<>();
         adapter = new ArrayAdapter<User>(Delete.this, android.R.layout.simple_list_item_1,listUser);
         lvDisplay.setAdapter(adapter);


         mDatabase.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for(DataSnapshot d:snapshot.getChildren()){
                     User u = d.getValue(User.class);
                     listUser.add(u);
                     setView();
                     adapter.notifyDataSetChanged();
                 }
             }
             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


         lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener(){
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 AlertDialog.Builder del = new AlertDialog.Builder(Delete.this);
                 del.setMessage("Are you sure ??").setCancelable(false)
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 User user = (User) parent.getItemAtPosition(position);
//                                 Toast.makeText(Delete.this,user.getUser(),Toast.LENGTH_SHORT).show();
                                 mDatabase.child("user").child(user.getUser()).removeValue();
                                 Intent intent = new Intent(Delete.this,Delete.class);
                                 startActivity(intent);
                             }
                         })
                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.cancel();
                             }
                         });
                 User user = (User) parent.getItemAtPosition(position);
                 AlertDialog alt = del.create();
                 alt.setTitle("Delete User : "+ user.getUser()+ "?");
                 alt.show();
             }
         });
     }
     public void setView(){
         adapter = new ArrayAdapter<User>(Delete.this, android.R.layout.simple_list_item_1,listUser);
         lvDisplay.setAdapter(adapter);
     }

}