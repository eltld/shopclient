package com.wy.chatclient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;
import java.util.Date;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.adapter.ChatAdapter;
import com.wy.vo.Content;

public class ChatDeatailAct extends BaseActivity {

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
        channel = ChatMainAct.getChannel();
        adapter = new ChatAdapter(new ArrayList<Content>(), activity);
        channel.pipeline().addLast(new ChatClientHandler(chatList, this));
        chatList.setAdapter(adapter);
        sendBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final Content content = new Content();
                content.setDate(new Date());
                content.setMsg(input.getText().toString());
                input.setText("");
                content.setName("wangyi");
                content.setTo(0);
                content.setToAll(true);
                lastWriteFuture = channel.writeAndFlush(content);
                lastWriteFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            ChatDeatailAct.this.runOnUiThread(new Runnable() {
                                
                                @Override
                                public void run() {
                                    adapter.addItem(content, adapter.getCount());
                                    chatList.setSelection(adapter.getCount()-1);  
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
