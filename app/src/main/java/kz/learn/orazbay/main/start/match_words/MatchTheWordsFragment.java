package kz.learn.orazbay.main.start.match_words;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.data.ControllerWords;
import kz.learn.orazbay.main.AnimationInitter;
import kz.learn.orazbay.models.Word;
import kz.learn.orazbay.utils.MyAnimationHelper;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

/**
 * Created by orazbay on 11/8/17.
 */

public class MatchTheWordsFragment extends MyAbstractFragment implements MatchTheWords {
    private int pairsLeft;
    ArrayList<CardWord> words;
    private CardWord clickedWord=null;

    private KonfettiView celebrationView;
    private LinearLayout row1,row2,row3,row4;

    private AlertDialog alertDialog;

    String [] actions={"Начать заново","Завершить"};

    ArrayList<Integer> btnIds=new ArrayList<>(Arrays.asList(new Integer[]{
            R.id.word1,
            R.id.word2,
            R.id.word3,
            R.id.word4,
            R.id.word5,
            R.id.word6,
            R.id.word7,
            R.id.word8,
            R.id.word9,
            R.id.word10,
            R.id.word11,
            R.id.word12}));

    public  MatchTheWordsFragment(){
        super(MatchTheWordsFragment.class.getName(),R.layout.fragment_match_the_words);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        view=inflater.inflate(R.layout.fragment_match_the_words,container,false);
        findViews();
        createAlterDialog();
        start();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void findViews() {
        celebrationView=view.findViewById(R.id.celebrationView);
        row1=view.findViewById(R.id.row1);
        row2=view.findViewById(R.id.row2);
        row3=view.findViewById(R.id.row3);
        row4=view.findViewById(R.id.row4);
    }
    private void init(){
        Collections.shuffle(btnIds);
        Iterator iterator=btnIds.iterator();
        words=new ArrayList<>();
        for (Object object:ControllerWords.getRandomWords(null,6)){
            Word word=(Word)object;
            Button buttonKZ=view.findViewById((Integer)iterator.next());
            buttonKZ.setVisibility(View.VISIBLE);
            CardWord cardKz=new CardWord(MatchTheWordsFragment.this,word.getKz(),word,buttonKZ);
            words.add(cardKz);

            Button buttonRus=view.findViewById((Integer)iterator.next());
            buttonRus.setVisibility(View.VISIBLE);
            CardWord cardRus=new CardWord(MatchTheWordsFragment.this,word.getRus(),word,buttonRus);
            words.add(cardRus);

        }
        Log.e(logTAG,words.size()+"");
    }
    @Override
    public void onWordClicked(final CardWord cardWord) {
        Log.e(logTAG,"onClicked "+cardWord.getWord().getRus());
        if (clickedWord!=null){//already have disabled button and chosen word
            if (clickedWord!=cardWord) {
                if (clickedWord.getWord()==cardWord.getWord()) {//correct pair
                    pairsLeft--;
                    hideFoundButtons(clickedWord.getView());
                    hideFoundButtons(cardWord.getView());
                    if (pairsLeft==0){
                        alertDialog.show();
                        celebrationView.build()
                                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                                .setDirection(0.0, 359.0)
                                .setSpeed(1f, 5f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(200000L)
                                .addShapes(Shape.RECT, Shape.CIRCLE)
                                .addSizes(new Size(12, 5f))
                                .setPosition(-50f, celebrationView.getWidth() + 50f, -50f, -50f)
                                .stream(300, 5000L);
                    }
                }else{
                    cardWord.setButtonState();
                    clickedWord.setButtonState();
                    MyAnimationHelper.invokeForView(context,cardWord.getView(),R.anim.shake,null);
                    MyAnimationHelper.invokeForView(context,clickedWord.getView(),R.anim.shake,null);
                }
            }
            clickedWord=null;
        }else{
            clickedWord=cardWord;
        }
    }
    public void showWords() {
        MyAnimationHelper.invokeForView(context,row1,R.anim.slide_in_down,null);
        MyAnimationHelper.invokeForView(context,row2,R.anim.slide_in_right,null);
        MyAnimationHelper.invokeForView(context,row3,R.anim.slide_in_left,null);
        MyAnimationHelper.invokeForView(context,row4,R.anim.slide_in_up,null);
    }

    private void createAlterDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Отлично! Выберите действие:");
        builder.setCancelable(false);
        builder.setItems(actions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        start();
                        break;
                    case 1:
                        context.onBackPressed();
                        break;
                }
            }
        });
        alertDialog=builder.create();
    }
    private void start(){
        celebrationView.reset();
        Log.e(logTAG,"onStartGame");
        pairsLeft=6;
        init();
        showWords();
    }
    private void hideFoundButtons(final Button button){
        MyAnimationHelper.invokeForView(context, button, R.anim.fade_out, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                button.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
