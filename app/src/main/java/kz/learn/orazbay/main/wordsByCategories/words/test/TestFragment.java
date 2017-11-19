package kz.learn.orazbay.main.wordsByCategories.words.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.custom.views.FittingTextView;
import kz.learn.orazbay.data.ControllerWords;
import kz.learn.orazbay.models.Word;
import kz.learn.orazbay.utils.MyAnimationHelper;

/**
 * Created by orazbay on 10/30/17.
 */

public class TestFragment extends MyAbstractFragment implements View.OnClickListener,Animation.AnimationListener {
    FittingTextView wordToAskTV;
    LinearLayout firstButtonsLayout,secondButtonsLayout;
    TextView backgroundTV,tvEnd;
    Button restartBtn,finishBtn;


    ArrayList<Object> words;

    Random random=new Random();

    private int currenIndexOfQuestion=0;
    private boolean translationDirection=true;
    private boolean isCurrentQuestionFailed=false;

    int [] buttonIds=new int[]{R.id.left_top_button,R.id.right_top_button,R.id.left_bottom_button,R.id.right_bottom_button};
    Button [] buttons=new Button[4];

    public static String WORDS_TYPE="WORDS_TYPE";

    private int correctAnswersCount;

    public final static int WORDS_BY_CATEGORY=0;
    public final static int WORDS_TO_LEARN=1;

    private int type;
    private String category=null;

    private String patternString ="%d из %d";



    public TestFragment(){
        super(TestFragment.class.getName(),R.layout.fragment_test);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findOutType();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        view=inflater.inflate(R.layout.fragment_test,container,false);
        findViews();

        initButtons();

        initWords();

        initQuestionsAndAnswers();

        return view;
    }

    @Override
    public void findViews() {
        wordToAskTV=view.findViewById(R.id.word_to_ask);

        firstButtonsLayout=view.findViewById(R.id.first_button_layout);
        secondButtonsLayout=view.findViewById(R.id.second_button_layout);

        backgroundTV=view.findViewById(R.id.backgroundTV);
        tvEnd=view.findViewById(R.id.tvEnd);

        restartBtn=view.findViewById(R.id.restartBtn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        finishBtn=view.findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.onBackPressed();
            }
        });


    }
    private void initQuestionsAndAnswers(){
        if (currenIndexOfQuestion!=words.size()){
            setQuestionConter();
            ArrayList<Object> answers=getAnswers();
            if (translationDirection){
                wordToAskTV.setText(((Word)words.get(currenIndexOfQuestion)).getKz());
                for (int i=0;i<4;i++){
                    buttons[i].setText(((Word)answers.get(i)).getRus());
                }
            }else{
                wordToAskTV.setText(((Word)words.get(currenIndexOfQuestion)).getRus());
                for (int i=0;i<4;i++){
                    buttons[i].setText(((Word)answers.get(i)).getKz());
                }
            }
            openWord();
            openButtons();
            setAll(View.VISIBLE);
        }else {//no any questions
                onQuestionsEnded();
//            context.onBackPressed();
        }

    }
    private ArrayList<Object> getAnswers(){
        ArrayList<Object> wordsWithoutCorrect=new ArrayList<>(words);
        wordsWithoutCorrect.remove(currenIndexOfQuestion);
        Collections.shuffle(wordsWithoutCorrect);

        wordsWithoutCorrect=new ArrayList<>(wordsWithoutCorrect.subList(0,3));
        wordsWithoutCorrect.add(words.get(currenIndexOfQuestion));
        Collections.shuffle(wordsWithoutCorrect);
        Log.e(logTAG,wordsWithoutCorrect.size()+"");

        return wordsWithoutCorrect;
    }
    private void initButtons(){
        for (int i=0;i<4;i++){
            Button button=view.findViewById(buttonIds[i]);
            buttons[i]=button;
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (currenIndexOfQuestion != words.size()) {
            Word currentWord = (Word) words.get(currenIndexOfQuestion);
            String answer = translationDirection ? currentWord.getRus() : currentWord.getKz();
            if (((Button) view).getText().toString().equals(answer)) {//correct response
                if (!isCurrentQuestionFailed){
                    correctAnswersCount++;
                    Log.e(logTAG,"correct "+correctAnswersCount);
                }
                closeWord();
                closeButtons();
                isCurrentQuestionFailed=false;
            }else {
                Log.e(logTAG,"not correct");
                isCurrentQuestionFailed=true;
                shakeView(view);
            }
        }
    }
    private void findOutType(){
        Bundle bundle=getArguments();
        type=bundle.getInt(WORDS_TYPE);
        category=bundle.getString("category",null);
    }
    private void initWords(){
        correctAnswersCount=0;
        switch (type){
            case WORDS_BY_CATEGORY:
                words= new ArrayList<>(Arrays.asList(ControllerWords.getWordsByCategory(category).toArray()));
                break;
            case WORDS_TO_LEARN:
                words= new ArrayList<>(Arrays.asList(ControllerWords.getWordsHaveToLearn().toArray()));
                break;
        }
        Collections.shuffle(words);
        translationDirection=random.nextBoolean();


    }
    private void next(){
        translationDirection=random.nextBoolean();
        currenIndexOfQuestion++;
        initQuestionsAndAnswers();
    }
    private void setAll(int visibilty){
        for (Button button:buttons){
            button.setVisibility(visibilty);
        }
        wordToAskTV.setVisibility(visibilty);
    }
    public void openButtons() {
        View[] viewsFirstRow = {
                view.findViewById(R.id.second_button_layout),
                view.findViewById(R.id.right_bottom_button),
                view.findViewById(R.id.left_bottom_button)};
        View[] viewsSecondRow = {
                view.findViewById(R.id.first_button_layout),
                view.findViewById(R.id.left_top_button),
                view.findViewById(R.id.right_top_button)};
        MyAnimationHelper.invokeForAllViews(context,viewsFirstRow, R.anim.float_in_up_first_row, null);
        MyAnimationHelper.invokeForAllViews(context,viewsSecondRow, R.anim.float_in_up_second_row, null);
    }
    public void closeButtons() {
        View[] viewsFirstRow = {
                view.findViewById(R.id.second_button_layout),
                view.findViewById(R.id.right_bottom_button),
                view.findViewById(R.id.left_bottom_button)};
        View[] viewsSecondRow = {
                view.findViewById(R.id.first_button_layout),
                view.findViewById(R.id.left_top_button),
                view.findViewById(R.id.right_top_button)};
        MyAnimationHelper.invokeForAllViews(context,viewsFirstRow, R.anim.float_away_down_first_row, null);
        MyAnimationHelper.invokeForAllViews(context,viewsSecondRow, R.anim.float_away_down_second_row, null);
    }

    private void closeWord() {
        MyAnimationHelper.invokeForView(context,wordToAskTV,R.anim.close_word,this);
    }
    public void openWord() {
        MyAnimationHelper.invokeForView(context,wordToAskTV,R.anim.open_word,null);
    }
    public void shakeView(View v) {
        MyAnimationHelper.invokeForView(context,v, R.anim.shake,null);
    }

    private void setQuestionConter(){
        backgroundTV.setText(String.format(patternString,currenIndexOfQuestion+1,words.size()));
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        setAll(View.INVISIBLE);
        next();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    private void onQuestionsEnded(){
        tvEnd.setVisibility(View.VISIBLE);
        backgroundTV.setText(String.format(patternString,correctAnswersCount,words.size()));
        restartBtn.setVisibility(View.VISIBLE);
        finishBtn.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FlipInY).playOn(restartBtn);
        YoYo.with(Techniques.FlipInY).playOn(finishBtn);
    }
    private void reset(){
        currenIndexOfQuestion=0;
        initQuestionsAndAnswers();
        tvEnd.setVisibility(View.INVISIBLE);
        restartBtn.setVisibility(View.INVISIBLE);
        finishBtn.setVisibility(View.INVISIBLE);

    }
}
