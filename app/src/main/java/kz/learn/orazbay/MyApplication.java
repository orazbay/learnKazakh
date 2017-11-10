package kz.learn.orazbay;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import kz.learn.orazbay.data.ControllerWords;
import kz.learn.orazbay.utils.Functions;

/**
 * Created by orazbay on 10/28/17.
 */

public class MyApplication extends Application {
    private final String logTAG=MyApplication.class.getName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(logTAG,"onCreate");
        Realm.init(this);

        if (Functions.getStringFromSP(this, ControllerWords.IS_WORDS_INSERTED).equals("null")){
            ControllerWords.insertWords();
            Scheduler.schedule(this);
            Functions.saveStringToSP(this,ControllerWords.IS_WORDS_INSERTED,"yes");
        }
    }
}
