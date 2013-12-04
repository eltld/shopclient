package com.wy.vo;

import java.io.Serializable;

public class User implements Serializable {

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

    public  User toObject(String str) {
        if (str.length() > 0) {
            String b[] = str.split(",");
            User o = new User();
            o.setName((String) b[0].subSequence(7, b[0].length()));
            o.setChannelId(Integer.valueOf((String) b[1].subSequence(11, b[1].length() - 1)));
            return o;
        } else {
            return null;
        }
    }
}
