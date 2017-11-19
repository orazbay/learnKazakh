package kz.learn.orazbay;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import kz.learn.orazbay.Receiver;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by orazbay on 11/1/17.
 */

public class Scheduler {
    private static String  logTAG=Scheduler.class.getName();
    private static int [] hoursToRemind=new int[]{9,11,13,15,18,19,21,23,0};
    public static void schedule(Context context){
        Log.e(logTAG,"schedule");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager!=null) {
            for (int hour : hoursToRemind) {
                Intent intent = new Intent(Receiver.ACTION);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                intent.putExtra("HOUR_OF_DAY", hour);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        }
    }
}
