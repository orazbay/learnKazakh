package kz.learn.orazbay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by orazbay on 10/28/17.
 */

public abstract class MyAbstractFragment extends Fragment {
    public String logTAG;
    public MainActivity context;
    public View view;
    private int layoutId;
    public MyAbstractFragment(String logTAG,int layoutId){
        this.logTAG=logTAG;
        this.layoutId=layoutId;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=(MainActivity)getActivity();
        Log.e(logTAG,"onCreate");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(logTAG,"onCreateView");
        view=inflater.inflate(layoutId,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.e(logTAG,"OnStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(logTAG,"OnResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(logTAG,"OnPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(logTAG,"OnStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(logTAG,"OnDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(logTAG,"onDestroy");
    }
    public abstract void findViews();

}
