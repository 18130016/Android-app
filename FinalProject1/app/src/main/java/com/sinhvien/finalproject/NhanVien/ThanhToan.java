package com.sinhvien.finalproject.NhanVien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sinhvien.finalproject.ChangeNumberItemListener;
import com.sinhvien.finalproject.R;

public class ThanhToan extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView txttongtien,txtempty,btntinhtien;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_thanh_toan);
        recyclerViewList = (RecyclerView)findViewById(R.id.recyclerviewlist);
        txttongtien=(TextView)findViewById(R.id.txt_tongtien);
        txtempty=(TextView)findViewById(R.id.txt_empty);
        scrollView=findViewById(R.id.scrollview);
        btntinhtien=(TextView)findViewById(R.id.btn_tinhtien);
        btntinhtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new AlertDialog.Builder(ThanhToan.this)
                         .setTitle("Hóa đơn")
                         .setMessage("Tổng chi phí là: "+txttongtien.getText())
                         .setPositiveButton("Ok",null)
                         .show();

            }
        });
        intList();
    }

    private void intList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        managementCart = new ManagementCart(this);
        adapter = new CartListAdapter(managementCart.getListCard(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCard();
            }
        });
        recyclerViewList.setLayoutManager(linearLayoutManager);
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCard().isEmpty()){
            txtempty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            txtempty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCard(){
        int tong = Math.round((managementCart.getTotalFee()*5/100)+managementCart.getTotalFee());
        txttongtien.setText(tong+"\t VNĐ");
    }
}