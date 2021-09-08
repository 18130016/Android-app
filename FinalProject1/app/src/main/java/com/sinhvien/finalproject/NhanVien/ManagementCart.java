package com.sinhvien.finalproject.NhanVien;

import android.content.Context;
import android.widget.Toast;


import com.sinhvien.finalproject.ChangeNumberItemListener;
import com.sinhvien.finalproject.QuanLi.Drink.Drink;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Drink item) {
        ArrayList<Drink> listFood = getListCard();
        boolean existAlready = false;
        int n = 0;
        String t = item.getName();
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getName().equals(t)) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready) {
            Toast.makeText(context, "Thêm số lượng sản phẩm trong giỏ hàng.", Toast.LENGTH_SHORT).show();
            listFood.get(n).setInCart(listFood.get(n).getInCart() + item.getInCart());
        } else {
            Toast.makeText(context, "Đã thêm vào giỏ hàng.", Toast.LENGTH_SHORT).show();
            listFood.add(item);
        }

        tinyDB.putListObject("CardList", listFood);

    }

    public ArrayList<Drink> getListCard() {
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<Drink> listfood, int position, ChangeNumberItemListener changeNumberItemsListener) {
        listfood.get(position).setInCart((int) (listfood.get(position).getInCart() + 1));
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public void MinusNumerFood(ArrayList<Drink> listfood, int position, ChangeNumberItemListener changeNumberItemsListener) {
        if (listfood.get(position).getInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setInCart((int) (listfood.get(position).getInCart() - 1));
        }
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public int getTotalFee() {
        ArrayList<Drink> listFood2 = getListCard();
        int fee = 0;
        for (int i = 0; i < listFood2.size(); i++) {
            fee = (int) (fee + (listFood2.get(i).getMoney() * listFood2.get(i).getInCart()));
        }
        return fee;
    }

}
