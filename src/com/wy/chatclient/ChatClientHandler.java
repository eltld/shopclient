package com.wy.chatclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.wy.shopping.constant.Const;
import com.wy.vo.Content;
import com.wy.vo.User;

public class ChatClientHandler extends SimpleChannelInboundHandler<Object> {

    private Activity act;

    public ChatClientHandler(Activity act) {
        this.act = act;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext arg0, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getCause());
        ctx.close();
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        return super.acceptInboundMessage(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Content) {
            final Content content = (Content) msg;
            content.setSendMsg(false);
            Intent intent = new Intent();
            ActivityManager am = (ActivityManager) act.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            //私聊消息
            if (content.getReceiveId() != 0) {
                if (!cn.getClassName().equals("com.wy.chatclient.ChatSingleAct")) {
                 // 构造一个user
                    User u = new User();
                    u.setChannelId(content.getReceiveId());
                    u.setName(content.getReceiveName());
                    if(!cn.getClassName().equals("com.wy.chatclient.ChatMainAct")){
                        NotificationUtil.sendNotify(act, ChatSingleAct.class,u,content);
                    }
                    intent.setAction("mymsg");
                    //指定接收消息者
                    intent.putExtra("user", u);
                }else{
                    intent.setAction(Const.ACTION_SINGLE_BROADCAST);
                }
            } else {
                //群聊消息
                intent.setAction(Const.ACTION_GROUP_BROADCAST);
                if (cn.getClassName().equals("com.wy.chatclient.ChatAllAct")) {
                }
            }
            intent.putExtra("msg", content);
            act.sendBroadcast(intent);
        }
        if (msg instanceof User) {
            final User user = (User) msg;
            Intent intent = new Intent();
            intent.setAction(Const.ACTION_ON_OR_OFF_LINE);
            intent.putExtra("user", user);
            act.sendBroadcast(intent);
        }

    }

}
