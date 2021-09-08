package com.sinhvien.finalproject.NhanVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.sinhvien.finalproject.QuanLi.Drink.Drink;
import com.sinhvien.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowOrder extends AppCompatActivity {
    private ImageView imgshow,imgremove,imgadd;
    private TextView txtshowmon,txtshowgia,txtsoluong;
    private Button btnthem;

    private int soluong = 1;
    private ManagementCart managementCart;


    private String drinkId;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_show_order);
        imgshow = (ImageView)findViewById(R.id.foodPic);
        txtshowmon=(TextView)findViewById(R.id.txt_showmon);
        txtshowgia=(TextView)findViewById(R.id.txt_showgia);
        imgremove = (ImageView)findViewById(R.id.img_remove);
        imgadd = (ImageView)findViewById(R.id.img_add);
        txtsoluong = (TextView)findViewById(R.id.txt_soluong);

        managementCart = new ManagementCart(this);
        btnthem = (Button)findViewById(R.id.btn_them);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        getBundle();
    }

    private void getBundle(){
        Intent getData = getIntent();
        drinkId = getData.getStringExtra("drinkId");
        ArrayList<Drink> list = new ArrayList<>();
        mDatabase.child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    Drink drink = d.getValue(Drink.class);
                    list.add(drink);
                }
                setInfor();
            }



            private void setInfor() {
                for(int i = 0; i< list.size();i++){
                    if(list.get(i).getName().equals(drinkId)){
                        Picasso.get().load(list.get(i).getImage()).into(imgshow);
                        txtshowmon.setText(list.get(i).getName());
                        txtshowgia.setText(""+list.get(i).getMoney()+"\t VND");
                        txtsoluong.setText(String.valueOf(soluong));
                        Drink object = new Drink(list.get(i).getId(),list.get(i).getName(),list.get(i).getImage(),list.get(i).getMoney(),1);
                        btnthem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                object.setInCart(soluong);
                                managementCart.insertFood(object);
                            }
                        });
                    }
                }

                imgadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soluong = soluong + 1;
                        txtsoluong.setText(String.valueOf(soluong));
                    }
                });

                imgremove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(soluong>1){
                            soluong = soluong - 1 ;
                        }
                        txtsoluong.setText(String.valueOf(soluong));
                    }
                });





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}