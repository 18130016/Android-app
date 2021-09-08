package com.sinhvien.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinhvien.finalproject.NhanVien.Hoadon;
import com.sinhvien.finalproject.QuanLi.QuanLiNhanVien;
import com.sinhvien.finalproject.QuanLi.User.User;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText edtendangnhap;
    EditText edmatkhau;
    Button btn_dangnhap;

    public DatabaseReference mDatabase;
    private ArrayList<User> listUser = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);

        edmatkhau = (EditText) findViewById(R.id.edmatkhau);
        edtendangnhap = (EditText) findViewById(R.id.edtendangnhap);
        btn_dangnhap = (Button) findViewById(R.id.btn_dangnhap);


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

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtendangnhap.getText().toString();
                String pass = edmatkhau.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else {
                    boolean c = false;
                    for (int i = 0; i < listUser.size(); i++) {
                        if ( checkAdmin(user,pass)  || (listUser.get(i).getUser().equals(user) && listUser.get(i).getPass().equals(pass) && listUser.get(i).getDepName().equals("Admin"))){
                            Toast.makeText(Login.this, "Đăng nhập Admin", Toast.LENGTH_SHORT).show();
                            c = true;
                            Intent intent = new Intent(Login.this, QuanLiNhanVien.class);
                            intent.putExtra("Username", user);
                            startActivity(intent);
                            finish();
                        }
                        if(user.equals(listUser.get(i).getUser()) && pass.equals(listUser.get(i).getPass()) && listUser.get(i).getDepName().equals("Staff")){
                            Toast.makeText(Login.this, "Đăng nhập Staff", Toast.LENGTH_SHORT).show();
                            c = true;
                            Intent intent = new Intent(Login.this, Hoadon.class);
                            intent.putExtra("Username", user);
                            startActivity(intent);
                            finish();
                        }
                    }

                    if(!c){
                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không đúng.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public boolean checkAdmin(String user,String pass){
        if(user.equals("Admin") && pass.equals("123")){
            return true;
        }else
        return false;
    }


}

