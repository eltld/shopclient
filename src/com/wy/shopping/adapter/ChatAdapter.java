package com.wy.shopping.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wy.shopping.R;
import com.wy.vo.Content;

public class ChatAdapter extends SimpleAdapter<Content> {

    public ChatAdapter(List<Content> data, Context activity) {
        super(data, activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Content item = getItem(position);
         Holder holder=null;
        if (convertView == null) {
            if (item.getTo() == 0) {
                convertView = makeView(R.layout.item_chat_send);
            } else {
                convertView = makeView(R.layout.item_chat_receive);
            }
            holder = new Holder();
            holder.msg = (TextView) convertView.findViewById(R.id.sender_msg);
            holder.name = (TextView) convertView.findViewById(R.id.sender_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (item != null) {
            holder.msg.setText(item.getMsg());
            holder.name.setText(item.getName());
        }
        return convertView;
    }

    private static class Holder {

        private TextView name;

        private TextView msg;
    }
}
