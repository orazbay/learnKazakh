package kz.learn.orazbay.main.alphabet;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.main.AnimationInitter;
import kz.learn.orazbay.main.alphabet.letter.Letter;
import kz.learn.orazbay.main.alphabet.letter.LetterFragment;
import kz.learn.orazbay.main.alphabet.letter.LetterTransition;
import kz.learn.orazbay.utils.ColorGenerator;
import kz.learn.orazbay.utils.Functions;
import kz.learn.orazbay.utils.MyAnimationHelper;

/**
 * Created by orazbay on 10/29/17.
 */

public class AlphabetFragment extends MyAbstractFragment implements AnimationInitter,View.OnClickListener {
    ArrayList<CardView> letterCards;
    int [] audioids={
            R.raw.letter1,
            R.raw.letter2,
            R.raw.letter3,
            R.raw.letter4,
            R.raw.letter5,
            R.raw.letter6,
            R.raw.letter7,
            R.raw.letter8,
            R.raw.letter9,
            R.raw.letter1,//jok i
            R.raw.letter11,
            R.raw.letter12,
            R.raw.letter13,
            R.raw.letter14,
            R.raw.letter15,
            R.raw.letter16,
            R.raw.letter17,
            R.raw.letter18,
            R.raw.letter19,
            R.raw.letter20,
            R.raw.letter21,
            R.raw.letter22,
            R.raw.letter23,
            R.raw.letter24,
            R.raw.letter25,
            R.raw.letter26,
            R.raw.letter27,
            R.raw.letter28,
            R.raw.letter29,
            R.raw.letter3,//jok ы
            R.raw.letter31,
            R.raw.letter32

    };
    public AlphabetFragment(){
        super(AlphabetFragment.class.getName(),R.layout.fragment_alphabet);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        view=inflater.inflate(R.layout.fragment_alphabet,container,false);

        findViews();
        initLetters();

//        setupToolBar();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void findViews() {

    }
    private void setupToolBar(){
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        context.setSupportActionBar(toolbar);
        ActionBar actionBar=context.getSupportActionBar();
        if (actionBar!=null){
            actionBar.setTitle(R.string.alphabet);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initLetters(){
        letterCards=new ArrayList<CardView>(Arrays.asList(new CardView[]{
                view.findViewById(R.id.letter1),
                view.findViewById(R.id.letter2),
                view.findViewById(R.id.letter3),
                view.findViewById(R.id.letter4),
                view.findViewById(R.id.letter5),
                view.findViewById(R.id.letter6),
                view.findViewById(R.id.letter7),
                view.findViewById(R.id.letter8),
                view.findViewById(R.id.letter9),
                view.findViewById(R.id.letter10),
                view.findViewById(R.id.letter11),
                view.findViewById(R.id.letter12),
                view.findViewById(R.id.letter13),
                view.findViewById(R.id.letter14),
                view.findViewById(R.id.letter15),
                view.findViewById(R.id.letter16),
                view.findViewById(R.id.letter17),
                view.findViewById(R.id.letter18),
                view.findViewById(R.id.letter19),
                view.findViewById(R.id.letter20),
                view.findViewById(R.id.letter21),
                view.findViewById(R.id.letter22),
                view.findViewById(R.id.letter23),
                view.findViewById(R.id.letter24),
                view.findViewById(R.id.letter25),
                view.findViewById(R.id.letter26),
                view.findViewById(R.id.letter27),
                view.findViewById(R.id.letter28),
                view.findViewById(R.id.letter29),
                view.findViewById(R.id.letter30),
                view.findViewById(R.id.letter31),
                view.findViewById(R.id.letter32)
        }));
        int i=0;
        for (CardView letterCard:letterCards){
            ViewCompat.setTransitionName(letterCard,"letter"+i);

            letterCard.setOnClickListener(this);

            letterCard.setCardBackgroundColor(Letter.letters.get(i).getColor());

            i++;
        }
    }
    @Override
    public void show() {
        MyAnimationHelper.invokeForView(context,view.findViewById(R.id.row1),R.anim.slide_in_down,null);
        MyAnimationHelper.invokeForView(context,view.findViewById(R.id.row2),R.anim.slide_in_left,null);
        MyAnimationHelper.invokeForView(context,view.findViewById(R.id.row3),R.anim.slide_in_right,null);
        MyAnimationHelper.invokeForView(context,view.findViewById(R.id.row4),R.anim.slide_in_left,null);
        MyAnimationHelper.invokeForView(context,view.findViewById(R.id.row5),R.anim.slide_in_right,null);
        MyAnimationHelper.invokeForView(context,view.findViewById(R.id.row6),R.anim.slide_in_up,null);
    }

    @Override
    public void onClick(View view) {
        int index=letterCards.indexOf(view);
        LetterFragment letterFragment=new LetterFragment();
        letterFragment.setCurrentLetter(index);
        letterFragment.setSharedElementEnterTransition(new LetterTransition());
        letterFragment.setSharedElementReturnTransition(new LetterTransition());
        letterFragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        context.getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, "letter"+index)
                .replace(R.id.container, letterFragment)
                .addToBackStack(LetterFragment.class.getName())
                .commit();
        System.out.println(index+1);
//        playAudio(index);


    }
}
