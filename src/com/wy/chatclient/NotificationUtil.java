package com.wy.chatclient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.wy.shopping.R;
import com.wy.shopping.activity.BaseActivity;
import com.wy.vo.Content;
import com.wy.vo.User;

public class NotificationUtil {

    public static void sendNotify(Context context, Class<? extends BaseActivity> target, User user, Content content) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification n = new Notification(R.drawable.noti_icon, "收到新通知", System.currentTimeMillis());
        n.flags = Notification.FLAG_AUTO_CANCEL;
        n.defaults = Notification.DEFAULT_ALL;

        Intent intent = new Intent(context, target);
        intent.putExtra("3", content);
        intent.putExtra("0", user);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        n.setLatestEventInfo(context, "收到新通知，请点击查看", "", pi);
        
        nm.notify(R.string.app_name, n);
    }
    
    
     public  void  sendBroadcast(Context act,Content content){
         Intent intent = new Intent();
         intent.putExtra("content",content );
         intent.setAction("123456");
         act.sendBroadcast(intent);
    }

}
