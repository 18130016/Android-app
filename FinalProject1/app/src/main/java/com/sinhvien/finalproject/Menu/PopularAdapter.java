package com.sinhvien.finalproject.Menu;

import android.annotation.SuppressLint;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.finalproject.NhanVien.ShowOrder;
import com.sinhvien.finalproject.QuanLi.Drink.Drink;
import com.sinhvien.finalproject.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<Drink> listDrink;

    public PopularAdapter(ArrayList<Drink> listDrink) {
        this.listDrink = listDrink;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mon.setText(listDrink.get(position).getName());
        holder.gia.setText(String.valueOf(listDrink.get(position).getMoney()));
        Picasso.get().load(listDrink.get(position).getImage()).into(holder.pic);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(holder.itemView.getContext(), ShowOrder.class);
                intent.putExtra("drinkId",listDrink.get(position).getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDrink.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mon,gia;
        ImageView pic;
        TextView addBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mon=itemView.findViewById(R.id.txt_mon);
            gia=itemView.findViewById(R.id.txt_gia);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addbtn);
        }
    }


}
