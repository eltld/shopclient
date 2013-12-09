package com.wy.shopping.constant;

public interface Const {
    public static final String SERVER_URL = "http://192.168.1.84:9000/service.do";
    // public static final String SERVER_URL =
    // "http://192.168.1.15:8080/service.do";

    /**私聊广播*/
    public static final String ACTION_SINGLE_BROADCAST = "com.wy.chatclient.ChatSingleAct";

    /**群聊广播*/
    public static final String ACTION_GROUP_BROADCAST = "com.wy.chatclient.ChatAllAct";

    /**上下线广播*/
    public static final String ACTION_ON_OR_OFF_LINE = "com.wy.chatclient.ChatMainAct";
    
    /***/
    public static final String ACTION_NOTIFI_BROADCAST = "com.wy.chatclient.Notifi";
}
