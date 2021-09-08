package com.sinhvien.finalproject.QuanLi.Drink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhvien.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DrinkAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    ArrayList<Drink> listDrink;

    public DrinkAdapter(Context myContext, int myLayout, ArrayList<Drink> listDrink) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.listDrink = listDrink;
    }

    @Override
    public int getCount() {
        return listDrink.size();
    }

    @Override
    public Object getItem(int position) {
        return listDrink.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{

            ImageView imgDrink;
            TextView dname;
            TextView dtype;
            TextView dprice;
            TextView dscore;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        ViewHolder holder = new ViewHolder();

        if(rowView == null){
            rowView = inflater.inflate(myLayout,null);
            holder.imgDrink = (ImageView) rowView.findViewById(R.id.listDimg);
            holder.dname = (TextView) rowView.findViewById(R.id.listDname);
            holder.dtype = (TextView)  rowView.findViewById(R.id.listDtype);
            holder.dprice = (TextView) rowView.findViewById(R.id.listDprice);
            holder.dscore = (TextView) rowView.findViewById(R.id.listDscore);
            rowView.setTag(holder);


        }else {
            holder = (ViewHolder) rowView.getTag();
        }

        Picasso.get().load(listDrink.get(position).getImage()).into(holder.imgDrink);
        holder.dname.setText("Tên : "+listDrink.get(position).getName());
        holder.dtype.setText("Loại : "+listDrink.get(position).getTypeName());
        holder.dprice.setText("Giá : " + listDrink.get(position).getMoney()+"đ");
        holder.dscore.setText("Điểm : " + listDrink.get(position).getPoint());

        return rowView;
    }
}
