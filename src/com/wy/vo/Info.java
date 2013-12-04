package com.wy.vo;

import java.io.Serializable;

public class Info implements Serializable{

    private int id;
    
    private String name;
    
    private String  pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Info [id=" + id + ", name=" + name + ", pass=" + pass + "]";
    }

}
