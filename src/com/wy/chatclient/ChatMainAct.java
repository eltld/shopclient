package com.wy.chatclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.adapter.ChatAdapter;

public class ChatMainAct extends BaseActivity {

    private static String IP = "192.168.1.84";
    private static int PORT = 9527;

    private Button send, close, start;
    
    private EditText input, showdata;

    public static Channel channel;

    ChannelFuture lastWriteFuture = null;
    
    EventLoopGroup group;

    public static Bootstrap bootStrap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        send = (Button) findViewById(R.id.send);
        close = (Button) findViewById(R.id.close);
        start = (Button) findViewById(R.id.start);
        input = (EditText) findViewById(R.id.input);
        showdata = (EditText) findViewById(R.id.showdata);
        start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                group = new NioEventLoopGroup();
                bootStrap= new Bootstrap();
                bootStrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChatClientInitializer());
                try {
                    bootStrap.option(ChannelOption.SO_KEEPALIVE, true);
                    bootStrap.option(ChannelOption.TCP_NODELAY, true);
                    bootStrap.option(ChannelOption.SO_REUSEADDR, true);
                    channel =bootStrap.connect(IP, PORT).sync().channel();
                    if(channel.isRegistered()){
                        setChannel(channel);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 

            }
        });

        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                String msg = input.getText().toString();
//                Content content = new Content();
//                content.setDate(new Date());
//                content.setMsg(msg);
//                content.setToAll(true);
//                lastWriteFuture = channel.writeAndFlush(content);
//                toast(lastWriteFuture.isSuccess());
                skip(ChatDeatailAct.class);
            }
        });

        close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //                    channel.close().sync();
                group.shutdownGracefully();
            }
        });
    }

    public Activity getBase() {
        return this;
    }

    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        ChatMainAct.channel = channel;
    }

    public static Bootstrap getBootStrap() {
        return bootStrap;
    }
    
}
