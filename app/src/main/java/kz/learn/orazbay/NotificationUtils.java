package kz.learn.orazbay;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import kz.learn.orazbay.utils.Functions;

/**
 * Created by orazbay on 11/3/17.
 */

public class NotificationUtils {
    public static int FLAG_NOTIFY_LEARN=1;
    public static void notifyLearn(Context context){
        NotificationCompat.Builder notifBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getString(R.string.time_to_learn))
                        .setContentText(context.getString(R.string.consolidate_success_by_test))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notifBuilder.setSound(alarmSound);
        Notification notification=notifBuilder.build();

        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra("FLAG_NOTIFY_LEARN",FLAG_NOTIFY_LEARN);

        notification.contentIntent=PendingIntent.getActivity(context,0,intent,0);;

        Functions.wakeUpScreen(context);

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager!=null) {
            notificationManager.notify(FLAG_NOTIFY_LEARN, notification);
        }


    }
}
