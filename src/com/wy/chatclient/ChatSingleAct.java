package com.wy.chatclient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.wy.shopping.utils.Util;
import com.wy.vo.Content;
import com.wy.vo.User;

public class ChatSingleAct extends BaseActivity {

    @ViewInject(id = R.id.lv_chat_detail)
    private ListView chatList;

    @ViewInject(id = R.id.send)
    private Button sendBtn;

    @ViewInject(id = R.id.content)
    private EditText input;

    ChannelFuture lastWriteFuture = null;

    ChatAdapter adapter;

    private Channel channel;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail);
        channel = LoginAct.getChannel();
        final User vo = (User) getVo("0");
        final Content msg = (Content) getIntent().getExtras().getSerializable("3");
        final List<Content> msgs = (List<Content>) getVo("1");
        if (Util.isEmpty(msgs)) {
            adapter = new ChatAdapter(new ArrayList<Content>(), activity);
        } else {
            adapter = new ChatAdapter(msgs, activity);
        }
        if (msg != null) {
            adapter.addItem(msg, 0);
            ChatMainAct.receivedMsgs.remove(vo.getChannelId());
            sendBroadcast(ChatSingleAct.this,msg);
        }
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
                content.setSendId(channel.hashCode());
                if (msg != null) {
                    content.setReceiveName(msg.getSendName());
                    content.setReceiveId(msg.getSendId());
                } else {
                    if (!Util.isEmpty(msgs)) {
                        content.setReceiveId(msgs.get(0).getSendId());
                        content.setReceiveName(msgs.get(0).getSendName());
                    } else {
                        content.setReceiveName(vo.getName());
                        content.setReceiveId(vo.getChannelId());
                    }
                }
                lastWriteFuture = channel.writeAndFlush(content);
                lastWriteFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            ChatSingleAct.this.runOnUiThread(new Runnable() {

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
            if (Const.ACTION_SINGLE_BROADCAST.equals(intent.getAction())) {
                Content content = (Content) intent.getSerializableExtra("msg");
                adapter.addItem(content, adapter.getCount());
                chatList.setSelection(adapter.getCount() - 1);
            }
        }

    }

    public void registerBoradcastReceiver(BroadcastReceiver receiver) {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Const.ACTION_SINGLE_BROADCAST);
        // 注册广播
        registerReceiver(receiver, myIntentFilter);
    }
    
    /**
     * 	
     *  @desc:发送广播到聊天列表界面，删除掉列表上显示的条数
     *  @author WY  
     *  创建时间 2013年12月9日 下午1:43:23
     */
    public void sendBroadcast(Context act, Content content){
        Intent intent = new Intent ();
        intent.setAction("123456");
        intent.putExtra("content", content);
        act.sendBroadcast(intent);
        
    }
}
