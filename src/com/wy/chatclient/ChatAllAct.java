package com.wy.chatclient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;
import java.util.Date;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.adapter.ChatAdapter;
import com.wy.shopping.constant.Const;
import com.wy.shopping.tasks.business.LoginTask;
import com.wy.vo.Content;

public class ChatAllAct extends BaseActivity {

    @ViewInject(id = R.id.lv_chat_detail)
    private ListView chatList;

    @ViewInject(id = R.id.send)
    private Button sendBtn;

    @ViewInject(id = R.id.content)
    private EditText input;

    ChannelFuture lastWriteFuture = null;

    ChatAdapter adapter;

    private Channel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail);
        channel = LoginAct.getChannel();
        adapter = new ChatAdapter(new ArrayList<Content>(), activity);
        chatList.setAdapter(adapter);
        registerBoradcastReceiver(new msgBroadcastReceiver());
        sendBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final Content content = new Content();
                content.setDate(new Date());
                content.setMsg(input.getText().toString());
                input.setText("");
                content.setSendName(LoginTask.SEND_NAME);
                content.setSendMsg(true);
                content.setReceiveId(0);
                lastWriteFuture = channel.writeAndFlush(content);
                lastWriteFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            ChatAllAct.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    adapter.addItem(content, adapter.getCount());
                                    chatList.setSelection(adapter.getCount() - 1);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    class msgBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Const.ACTION_GROUP_BROADCAST.equals(intent.getAction())) {
                Content content = (Content) intent.getSerializableExtra("msg");
                adapter.addItem(content, adapter.getCount());
                chatList.setSelection(adapter.getCount() - 1);
            }
        }

    }

    public void registerBoradcastReceiver(BroadcastReceiver receiver) {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Const.ACTION_GROUP_BROADCAST);
        // 注册广播
        registerReceiver(receiver, myIntentFilter);
    }
}
