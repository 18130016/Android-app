package com.sinhvien.finalproject.QuanLi.Drink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.sinhvien.finalproject.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class Add extends AppCompatActivity {

    EditText edName,edMoney,edPoint;
    Spinner spType;
    ImageView ivImage;
    Button btnAdd,btnImage;

    private DatabaseReference dDatabase;
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        dDatabase = FirebaseDatabase.getInstance().getReference();

        StorageReference storageRef = storage.getReferenceFromUrl("gs://android-app-2590.appspot.com");

        edName= findViewById(R.id.edName);
        edMoney = findViewById(R.id.edMoney);
        edPoint = findViewById(R.id.edPoint);
        spType = findViewById(R.id.spType);
        ivImage = findViewById(R.id.ivImage);
        btnAdd = findViewById(R.id.btnAdd);
        btnImage = findViewById(R.id.btnImage);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Coffee");
        arrayList.add("Food");
        arrayList.add("Juice");
        arrayList.add("Smoothie");
        arrayList.add("Tea");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,arrayList);
        spType.setAdapter(arrayAdapter);


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                StorageReference mountainsRef = storageRef.child(spType.getSelectedItem().toString() + calendar.getTimeInMillis() + ".png");
                String img = spType.getSelectedItem().toString() + calendar.getTimeInMillis() + ".png";
                String linkImg = "";

                ivImage.setDrawingCacheEnabled(true);
                ivImage.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(Add.this, "Up hình thất bại!!!",Toast.LENGTH_SHORT).show();

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                // Continue with the task to get the download URL
                                return mountainsRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();

                                    Drink drink = new Drink(edName.getText().toString(), edName.getText().toString(), spType.getSelectedItem().toString(),
                                    downloadUri.toString(), Long.parseLong(edMoney.getText().toString()),
                                    Long.parseLong(edPoint.getText().toString()));
                                    dDatabase.child("drink").child(drink.getName()).setValue(drink);
                                    Toast.makeText(Add.this, "Thêm thành công : " + drink.getId(),Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Add.this,List.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    public void onActivityResult(int requestcode, int resultCode, Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
        Context context = getApplicationContext();
        if (requestcode == 1&& resultCode == Activity.RESULT_OK){
            if (data == null){
                return;
            }
            Uri uri = data.getData();

            InputStream inputStream;
            try {
                inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivImage.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(this,"Unable to open",Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(context,uri.getPath(),Toast.LENGTH_SHORT).show();
        }
    }
}