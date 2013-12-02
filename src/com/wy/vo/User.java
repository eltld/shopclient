package com.wy.vo;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String name;
    
    private int channelId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", channelId=" + channelId + "]";
    }
   
}
