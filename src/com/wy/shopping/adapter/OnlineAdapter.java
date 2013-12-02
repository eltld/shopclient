package com.wy.shopping.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wy.shopping.R;
import com.wy.vo.User;

public class OnlineAdapter extends SimpleAdapter<User> {

    public OnlineAdapter(List<User> data, Context activity) {
        super(data, activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = makeView(R.layout.item_user_online);
            holder.name = (TextView) convertView.findViewById(R.id.user_name);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        User user = getItem(position);
        holder.name.setText(user.getName());
        return convertView;
    }

    class Holder {
        private TextView name;
    }
}
