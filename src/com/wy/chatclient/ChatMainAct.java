package com.wy.chatclient;

import java.util.List;

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

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.adapter.OnlineAdapter;
import com.wy.shopping.constant.Const;
import com.wy.vo.User;

public class ChatMainAct extends BaseActivity {

    private Button send, close, start;

    @ViewInject(id = R.id.online)
    private ListView onlineList;

    private OnlineAdapter adapter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        adapter = new OnlineAdapter((List<User>) getVo("0"), this);
        onlineList.setAdapter(adapter);
        registerBoradcastReceiver(new UserOnlinReceiver());

        send = (Button) findViewById(R.id.send);
        close = (Button) findViewById(R.id.close);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

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

    class UserOnlinReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Const.ACTION_ON_OR_OFF_LINE.equals(intent.getAction())) {
                User user = (User) intent.getSerializableExtra("user");
                // 上线
                if (user.getName() != null) {
                    adapter.addItem(user, adapter.getCount());
                }
                // 下线
                else {
                    List<User> onlineUsers = adapter.getDataSource();
                    for (int i = 0; i < onlineUsers.size(); i++) {
                        if (user.getChannelId() == onlineUsers.get(i).getChannelId()) {
                            adapter.remove(onlineUsers.get(i));
                            return;
                        }
                    }
                }
            }

        }

    }

    public void registerBoradcastReceiver(BroadcastReceiver receiver) {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Const.ACTION_ON_OR_OFF_LINE);
        // 注册广播
        registerReceiver(receiver, myIntentFilter);
    }
}
