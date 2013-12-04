package com.wy.chatclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

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
            if (content.getHashCode() != 0) {
                intent.setAction("singleMsg");
            } else {
                intent.setAction("allmsg");
            }
            intent.putExtra("msg", content);
            act.sendBroadcast(intent);
            ActivityManager am = (ActivityManager) act.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            System.out.println(cn.getClassName());
            System.out.println(cn.toString());
        }
        if (msg instanceof User) {
            final User user = (User) msg;
            Intent intent = new Intent();
            intent.setAction("online");
            intent.putExtra("user", user);
            act.sendBroadcast(intent);
        }

    }

}
