package com.ruc.bhushalsuman.ruc;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Bhushal Suman on 4/19/2016.
 */
public class Notify extends BroadcastReceiver {

    int notifyId;
    String notifyTitle;
    String notifyBody;

    NotificationManager nManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        notifyId = intent.getIntExtra("NOTIFICATION_ID", 1);
        notifyTitle = intent.getStringExtra("NOTIFICATION_TITLE");
        notifyBody = intent.getStringExtra("NOTIFICATION_BODY");
        createNotification(context, notifyId, notifyTitle, notifyBody);
    }

    private void createNotification(Context context, int notificationId, String notificationTitle, String notificationBody) {

        Intent notificationIntent = new Intent(context, ScheduleActivity.class);
        //user click back, goes to proper place
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(context);
        tStackBuilder.addParentStack(ScheduleActivity.class);
        // add Intent starts the Activity to the top of the stack
        tStackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        //pending intent (if exist Update)
        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(context)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setSmallIcon(R.drawable.ruc_icon)
                .setContentIntent(pendingIntent)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;


        nManager.notify(notificationId, notification);

    }

}
