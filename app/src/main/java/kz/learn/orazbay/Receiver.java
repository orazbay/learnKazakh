package kz.learn.orazbay;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import kz.learn.orazbay.data.ControllerWords;

/**
 * Created by orazbay on 11/1/17.
 */

public class Receiver extends BroadcastReceiver {
    public static String ACTION="SchedulerReceiverAction";
    String logTAG=Receiver.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
        int hour=intent.getIntExtra("HOUR_OF_DAY",-1);

        Log.e(logTAG,"onReceive, hour: "+hour);

        if (hour!=-1){
            if (hour==0){//midnight,so reset words

            }else{//time to learn words
                Log.e(logTAG,ControllerWords.getWordsHaveToLearn().size()+"");
                if (ControllerWords.getWordsHaveToLearn().size()>=4){
                    NotificationUtils.notifyLearn(context);
                }
            }
        }
    }
}
