package com.sinhvien.finalproject.QuanLi.Department;

public class Department {
    private String id;
    private String depName;

    public Department() {
    }

    public Department(String id, String name) {
        this.id = id;
        depName = name;
    }

    @Override
    public String toString() {
        return depName ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return depName;
    }

    public void setName(String name) {
        depName = name;
    }

}