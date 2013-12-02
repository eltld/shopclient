package com.wy.chatclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.ConnectException;
import java.util.ArrayList;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.adapter.OnlineAdapter;
import com.wy.vo.User;

public class ChatMainAct extends BaseActivity {

    private static String IP = "192.168.1.84";
    private static int PORT = 9527;

    private Button send, close, start;

    public static Channel channel;

    ChannelFuture lastWriteFuture = null;

    EventLoopGroup group;

    public static Bootstrap bootStrap;

    @ViewInject(id = R.id.online)
    private ListView onlineList;

    private OnlineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        adapter = new OnlineAdapter(new ArrayList<User>(), this);
        onlineList.setAdapter(adapter);
        registerBoradcastReceiver(new UserOnlinReceiver());

        send = (Button) findViewById(R.id.send);
        close = (Button) findViewById(R.id.close);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                group = new NioEventLoopGroup();
                bootStrap = new Bootstrap();
                bootStrap.group(group).channel(NioSocketChannel.class)
                        .handler(new ChatClientInitializer(ChatMainAct.this));
                bootStrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootStrap.option(ChannelOption.TCP_NODELAY, true);
                bootStrap.option(ChannelOption.SO_REUSEADDR, true);
                try {
                    channel = bootStrap.connect(IP, PORT).sync().channel();
                } catch (Exception e) {
                   if(e instanceof ConnectException){
                       toast("连接服务器失败");
                   }
                   System.err.println(e.fillInStackTrace());
                }
                if (channel != null && channel.isRegistered()) {
                    setChannel(channel);
                }

            }
        });

        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                skip(ChatAllAct.class);
            }
        });

        close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // channel.close().sync();
//                group.shutdownGracefully();
                User user = new User();
                user.setName("xxx");
                user.setChannelId(channel.hashCode());
                skip(ChatSingleAct.class, user);
            }
        });
        onlineList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int postion, long arg3) {
                User user = adapter.getItem(postion);
                skip(ChatSingleAct.class, user);
            }
        });
    }

    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        ChatMainAct.channel = channel;
    }

    class UserOnlinReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("online".equals(intent.getAction())) {
                User user = (User) intent.getSerializableExtra("user");
                adapter.addItem(user, adapter.getCount());
            }

        }

    }

    public void registerBoradcastReceiver(BroadcastReceiver receiver) {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("online");
        // 注册广播
        registerReceiver(receiver, myIntentFilter);
    }
}
