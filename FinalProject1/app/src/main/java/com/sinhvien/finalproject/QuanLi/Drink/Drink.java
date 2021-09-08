package com.sinhvien.finalproject.QuanLi.Drink;
import android.graphics.Bitmap;
public class Drink {
    private String id;
    private String Name;
    private String TypeName;
    private String Image;
    private long money;
    private long point;
    private int inCart;

    public Drink() {
    }

    public Drink(String id, String name, String typeName, String image, long money, long point) {
        this.id = id;
        Name = name;
        TypeName = typeName;
        Image = image;
        this.money = money;
        this.point = point;
    }

    public Drink(String id, String name, String image, long money,int inCart) {
        this.id = id;
        Name = name;
        Image = image;
        this.money = money;
        this.inCart = inCart;
    }

    @Override
    public String toString() {
        return
                "Name: " + Name +
                        ", TypeName: " + TypeName;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public int getInCart() {
        return inCart;
    }

    public void setInCart(int inCart) {
        this.inCart = inCart;
    }
}
