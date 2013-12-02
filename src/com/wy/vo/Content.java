package com.wy.vo;

import java.io.Serializable;
import java.util.Date;


public class Content implements Serializable{
    
    private  static final long serialVersionUID = 2L;
    
    /**发送者名字*/
    private String name;

    /**发送日期*/
    private Date date;

    /**发送内容*/
    private String msg;

    /**true:发送出去；false：接受*/
    private boolean isSendMsg;

    /**0：群发；!=0:点对点*/
    private int hashCode;

    public String getName() {
        return name;
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

    public boolean isSendMsg() {
        return isSendMsg;
    }

    public void setSendMsg(boolean isSendMsg) {
        this.isSendMsg = isSendMsg;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Content [name=" + name + ", date=" + date + ", msg=" + msg + ", isSendMsg=" + isSendMsg + ", hashCode="
                + hashCode + "]";
    }

}
