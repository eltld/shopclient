package com.wy.chatclient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.annotation.view.ViewInject;
import android.annotation.SuppressLint;
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
import android.widget.RelativeLayout;

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.adapter.OnlineAdapter;
import com.wy.shopping.constant.Const;
import com.wy.shopping.utils.Util;
import com.wy.vo.Content;
import com.wy.vo.User;

public class ChatMainAct extends BaseActivity {

    private Button send, close, start;

    @ViewInject(id = R.id.online)
    private ListView onlineList;

    private OnlineAdapter adapter;

    @SuppressLint("UseSparseArrays")
    public static Map<Integer, List<Content>> receivedMsgs = new HashMap<Integer, List<Content>>();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        adapter = new OnlineAdapter(new ArrayList<User>(), this);
        onlineList.setAdapter(adapter);
        if (!Util.isEmpty((List<User>) getVo("0"))) {
            adapter.addItems((List<User>) getVo("0"));
        }
        registerBoradcastReceiver(new UserOnlineReceiver());
        registerBoradcastMsg(new SingleChatReceiver());
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
            public void onItemClick(AdapterView<?> arg0, View view, int postion, long arg3) {
                User user = adapter.getItem(postion);
                List<Content> msgs = receivedMsgs.get(user.getChannelId());
                if (!Util.isEmpty(msgs)) {
                    receivedMsgs.remove(user.getChannelId());
                    RelativeLayout rl = (RelativeLayout) view;
                    View tipsView = rl.getChildAt(1);
                    tipsView.setVisibility(View.GONE);
                }
                skip(ChatSingleAct.class, user, (Serializable) msgs);
            }
        });
    }

    class UserOnlineReceiver extends BroadcastReceiver {

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

    class SingleChatReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("mymsg".equals(intent.getAction())) {
                Content content = (Content) intent.getSerializableExtra("msg");
                List<Content> msgs = receivedMsgs.get(content.getSendId());
                if (Util.isEmpty(msgs)) {
                    msgs = new ArrayList<Content>();
                }
                msgs.add(content);
                receivedMsgs.put(content.getSendId(), msgs);
                List<User> onlineUsers = adapter.getDataSource();
                for (int i = 0; i < onlineUsers.size(); i++) {
                    if (content.getSendId() == onlineUsers.get(i).getChannelId()) {
                        if (receivedMsgs.get(content.getSendId()).size() > 0) {
                            int position = adapter.getPosition(onlineUsers.get(i));
                            RelativeLayout rl = (RelativeLayout) onlineList.getChildAt(position);
                            System.out.println(rl.getChildAt(1));
                            BadgeView tips = new BadgeView(ChatMainAct.this, rl.getChildAt(1));
                            tips.setText(receivedMsgs.get(content.getSendId()).size() + "");
                            tips.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                            tips.toggle(null, null);
                            System.out.println(rl.getChildAt(1));
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

    public void registerBoradcastMsg(BroadcastReceiver receiver) {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("mymsg");
        // 注册广播
        registerReceiver(receiver, myIntentFilter);
    }
}
