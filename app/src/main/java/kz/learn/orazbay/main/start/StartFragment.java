package kz.learn.orazbay.main.start;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.BaseTransientBottomBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.main.AnimationInitter;
import kz.learn.orazbay.main.settings.NameDilaogInterface;
import kz.learn.orazbay.main.settings.SettingsFragment;
import kz.learn.orazbay.main.start.match_words.MatchTheWordsFragment;
import kz.learn.orazbay.utils.Functions;
import kz.learn.orazbay.utils.MyAnimationHelper;

/**
 * Created by orazbay on 11/11/17.
 */

public class StartFragment extends MyAbstractFragment implements View.OnClickListener,AnimationInitter{
    private LinearLayout firstColumn,secondColumn;
    private Button button1,button2,button3,button4;

    public StartFragment(){
        super(StartFragment.class.getName(),R.layout.fragment_start);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        view=inflater.inflate(R.layout.fragment_start,container,false);

        findViews();

        button1.setOnClickListener(this);
        button3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setVisibility(View.VISIBLE);
                show();
                if (Functions.getStringFromSP(context,"name").equals("null")){
                    SettingsFragment.showDialogName(context, false, new NameDilaogInterface() {
                        @Override
                        public void onNameEntered(String name) {
                            Functions.saveStringToSP(context,"name",name);
                        }
                    });
                }
            }
        },100);
    }

    @Override
    public void onPause() {
        super.onPause();
        setVisibility(View.INVISIBLE);
    }

    @Override
    public void findViews() {
        firstColumn=view.findViewById(R.id.firstColumn);
        secondColumn=view.findViewById(R.id.secondColumn);

        button1=view.findViewById(R.id.button1);//match the words
        button2=view.findViewById(R.id.button2);//soon
        button3=view.findViewById(R.id.button3);//questions
        button4=view.findViewById(R.id.button4);//soon
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                Functions.ReplaceFragmentWithStack(context,new MatchTheWordsFragment());
                break;
            case R.id.button3:

                break;
        }
    }
    private void setVisibility(int visibility){
        firstColumn.setVisibility(visibility);
        secondColumn.setVisibility(visibility);
    }

    @Override
    public void show() {
        MyAnimationHelper.invokeForView(context,firstColumn,R.anim.slide_in_left,null);
        MyAnimationHelper.invokeForView(context,secondColumn,R.anim.slide_in_right,null);
//        MyAnimationHelper.invokeForView(context,button1,R.anim.slide_in_left,null);
//        MyAnimationHelper.invokeForView(context,button3,R.anim.slide_in_down,null);
//        MyAnimationHelper.invokeForView(context,button2,R.anim.slide_in_up,null);
//        MyAnimationHelper.invokeForView(context,button4,R.anim.slide_in_right,null);
    }
}
