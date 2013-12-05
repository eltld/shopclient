package com.wy.vo;

import java.io.Serializable;
import java.util.Date;


public class Content implements Serializable{
    
    private  static final long serialVersionUID = 2L;
    
    /**发送者名字*/
    private String sendName;

    /**接受人姓名*/
    private String receiveName;
    
    /**发送日期*/
    private Date date;

    /**发送内容*/
    private String msg;

    /**true:发送出去；false：接受*/
    private boolean isSendMsg;

    /**0：群发；!=0:点对点:目标channelId*/
    private int receiveId;

    private int sendId;

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


    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    @Override
    public String toString() {
        return "Content [sendName=" + sendName + ", receiveName=" + receiveName + ", date=" + date + ", msg=" + msg
                + ", isSendMsg=" + isSendMsg + ", receiveId=" + receiveId + ", sendId=" + sendId + "]";
    }

}
