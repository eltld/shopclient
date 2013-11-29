package com.wy.vo;

import java.io.Serializable;
import java.util.Date;

public class Content implements Serializable{
    
    private static final long serialVersionUID = 2L;
    
    /**发送者名字*/
    private String name;

    private Date date;

    private String msg;

    /**0:发送出去；1：接受*/
    private int to;

    private boolean isToAll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public boolean isToAll() {
        return isToAll;
    }

    public void setToAll(boolean isToAll) {
        this.isToAll = isToAll;
    }

    @Override
    public String toString() {
        return "Content [name=" + name + ", date=" + date + ", msg=" + msg + ", to=" + to + ", isToAll=" + isToAll
                + "]";
    }

}
