package kz.learn.orazbay.main.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.main.AnimationInitter;
import kz.learn.orazbay.main.start.match_words.MatchTheWordsFragment;
import kz.learn.orazbay.utils.Functions;
import kz.learn.orazbay.utils.MyAnimationHelper;

/**
 * Created by orazbay on 11/11/17.
 */

public class StartFragment extends MyAbstractFragment implements View.OnClickListener,AnimationInitter{
    private View view;
    private LinearLayout firstColumn,secondColumn;
    private Button button1,button2,button3,button4;

    public StartFragment(){
        super(StartFragment.class.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_start,container,false);

        findViews();

        button1.setOnClickListener(this);
        button3.setOnClickListener(this);

        show();

        return view;
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
