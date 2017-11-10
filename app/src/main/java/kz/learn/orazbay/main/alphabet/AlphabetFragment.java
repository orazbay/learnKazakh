package kz.learn.orazbay.main.alphabet;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.time.temporal.TemporalQueries;
import java.util.ArrayList;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;

/**
 * Created by orazbay on 10/29/17.
 */

public class AlphabetFragment extends MyAbstractFragment {
    private View view;
    CardView [] letterCards;
    CardView letter1;
    public AlphabetFragment(){
        super(AlphabetFragment.class.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_alphabet,container,false);
        findViews();
        initLetters();
        setupToolBar();
        return view;
    }

    @Override
    public void findViews() {
        letter1=view.findViewById(R.id.letter1);

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
        animateLetter(0);
    }
    private void animateLetter(final int index){
        if (index<letterCards.length){
            letterCards[index].setVisibility(View.VISIBLE);
            YoYo.with(Techniques.ZoomIn).duration(200).onEnd(new YoYo.AnimatorCallback() {
                @Override
                public void call(Animator animator) {
                    animateLetter(index+1);
                }
            }).playOn(letterCards[index]);
        }
    }

    private void initLetters(){
        letterCards=new CardView[]{
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
        };
        for (CardView letterCard:letterCards){
            letterCard.setVisibility(View.INVISIBLE);
            ColorGenerator generator = ColorGenerator.MATERIAL;
            letterCard.setCardBackgroundColor(generator.getRandomColor());

        }
    }
}
