package kz.learn.orazbay.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.PowerManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;

import kz.learn.orazbay.R;

import static android.content.Context.POWER_SERVICE;

/**
 * Created by root on 05.07.17.
 */

public class Functions {

    //Shared Preference
    public static void saveStringToSP(Context context,String key, String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }public static String getStringFromSP(Context context,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"null");
    }


    //Fragments manager
    public static void ReplaceFragmentWithStack(Context context,Fragment fragment) {
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.replace(R.id.container, fragment);
        manager.popBackStackImmediate(fragment.getClass().getName(),1);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();

    }
    public static  void RemoveFragment(Context context,Fragment fragment){
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        manager.popBackStack(fragment.getClass().getName(),1);
        transaction.commit();
    }
    public static void RemoveOldFragmentAndReplace(Context context,String oldFragmentName,Fragment newFragment){
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.replace(R.id.container, newFragment);
        manager.popBackStack(oldFragmentName,1);
        transaction.addToBackStack(newFragment.getClass().getName());
        transaction.commit();
    }
    public static void ReplaceFragment(Context context,Fragment fragment) {
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }public static void AddFragment(Context context,Fragment fragment){
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    public static void showSnackbar(View view, String s) {
            Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
    }
    public static void hideKeyboard(Context context) {
        View view = ((AppCompatActivity)context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm!=null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
    public static void showHint(Context context,String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
    public static boolean isAppInBackground(Context context1) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context1.getSystemService(Context.ACTIVITY_SERVICE);
        if (am!=null) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context1.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        }

        return isInBackground;
    }
    public static int dpToPx(Context context,int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public static void wakeUpScreen(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(POWER_SERVICE);
        if (pm != null) {
            boolean isScreenOn = pm.isScreenOn();
            Log.e("screen on", "" + isScreenOn);
            if (!isScreenOn) {
                PowerManager.WakeLock        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");
                wl.acquire(10000);
                PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");

                wl_cpu.acquire(10000);
            }
        }
    }

}
