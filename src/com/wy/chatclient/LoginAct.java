package com.wy.chatclient;

import java.net.ConnectException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.tasks.business.LoginTask;
import com.wy.vo.Info;

public class LoginAct extends BaseActivity {

    private static String IP = "192.168.1.84";
    private static int PORT = 9527;
    public static Channel channel;
    ChannelFuture lastWriteFuture = null;
    EventLoopGroup group;
    public static Bootstrap bootStrap;

    @ViewInject(id = R.id.login_name)
    private EditText name;

    @ViewInject(id = R.id.login_pass)
    private EditText pass;

    @ViewInject(id = R.id.login_login)
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        login = (Button) findViewById(R.id.login_login);
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Info info = new Info();
                info.setName(name.getText().toString());
                info.setPass(pass.getText().toString());
                new LoginTask(LoginAct.this).execute(info);
            }
        });
        connectServer();
    }

    public void connectServer() {
        group = new NioEventLoopGroup();
        bootStrap = new Bootstrap();
        bootStrap.group(group).channel(NioSocketChannel.class).handler(new ChatClientInitializer(LoginAct.this));
        bootStrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootStrap.option(ChannelOption.TCP_NODELAY, true);
        bootStrap.option(ChannelOption.SO_REUSEADDR, true);
        try {
            channel = bootStrap.connect(IP, PORT).sync().channel();
            System.out.println(channel.toString());
        } catch (Exception e) {
            if (e instanceof ConnectException) {
                toast("连接服务器失败");
            }
            System.err.println(e.fillInStackTrace());
            return;
        }
        if (channel != null && channel.isRegistered()) {
            setChannel(channel);
        }
    }

    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        LoginAct.channel = channel;
    }
}
