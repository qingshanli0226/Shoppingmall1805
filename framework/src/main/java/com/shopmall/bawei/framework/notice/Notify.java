package com.shopmall.bawei.framework.notice;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class Notify {

      @SuppressLint("NewApi")
      public static void setnotify(Context context, int num, String message, int samallimage, String title){
          Notification build = new Notification.Builder(context)
                  .setSmallIcon(samallimage)
                  .setContentTitle(title)
                  .setContentText(message)
                  .setWhen(System.currentTimeMillis())
                  .setDefaults(Notification.DEFAULT_ALL)//悬浮通知
                  .setPriority(Notification.PRIORITY_MAX)
                  .setAutoCancel(true)//点击后取消
                  .build();
          NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
          notificationManager.notify(num,build);


      }
}
