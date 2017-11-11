package kz.learn.orazbay.main.start.match_words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by orazbay on 11/8/17.
 */

public class MatchTheWordsFragment extends MyAbstractFragment implements MatchTheWords,AnimationInitter {
    private View view;
    private int pairsLeft=6;
    ArrayList<CardWord> words;
    private CardWord clickedWord=null;

    private LinearLayout row1,row2,row3,row4;

    Random random=new Random();
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
        super(MatchTheWordsFragment.class.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_match_the_words,container,false);
        findViews();
        init();
        showWords();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void findViews() {
        row1=view.findViewById(R.id.row1);
        row2=view.findViewById(R.id.row2);
        row3=view.findViewById(R.id.row3);
        row4=view.findViewById(R.id.row4);
    }
    private void init(){
        Collections.shuffle(btnIds);
        Iterator iterator=btnIds.iterator();
        words=new ArrayList<>();
        for (Object object:getRandom6Words()){
            Word word=(Word)object;
            Button buttonKZ=view.findViewById((Integer)iterator.next());
            CardWord cardKz=new CardWord(MatchTheWordsFragment.this,word.getKz(),word,buttonKZ);
            words.add(cardKz);

            Button buttonRus=view.findViewById((Integer)iterator.next());
            CardWord cardRus=new CardWord(MatchTheWordsFragment.this,word.getRus(),word,buttonRus);
            words.add(cardRus);

        }
        Log.e(logTAG,words.size()+"");
    }
    private List<Object> getRandom6Words(){
        ArrayList<Object> words= new ArrayList<>(Arrays.asList(ControllerWords.getAll().toArray()));
        Collections.shuffle(words);
        return words.subList(0,6);
    }
    @Override
    public void onWordClicked(final CardWord cardWord) {
        Log.e(logTAG,"onClicked "+cardWord.getWord().getRus());
        if (clickedWord!=null){//already have disabled button and chosen word
            if (clickedWord!=cardWord) {
                if (clickedWord.getWord()==cardWord.getWord()) {//correct pair
                    YoYo.with(Techniques.ZoomOut).duration(500).playOn(cardWord.getView());
                    YoYo.with(Techniques.ZoomOut).duration(500).playOn(clickedWord.getView());
                }else{
                    pairsLeft--;
                    cardWord.setButtonState();
                    clickedWord.setButtonState();
                    MyAnimationHelper.invokeForView(context,cardWord.getView(),R.anim.shake,null);
                    MyAnimationHelper.invokeForView(context,clickedWord.getView(),R.anim.shake,null);
                    if (pairsLeft==0){

                    }
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

    @Override
    public void show() {

    }
}
