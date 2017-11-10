package kz.learn.orazbay.match_words;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kz.learn.orazbay.R;
import kz.learn.orazbay.models.Word;

/**
 * Created by orazbay on 11/8/17.
 */

public class CardWord {
    private String logTAG=CardWord.class.getName();
    MatchTheWordsFragment context;
    private String text;
    private Word word;
    private Button view;
    private boolean isEnabled=true;
    public CardWord(final MatchTheWordsFragment context, String text, final Word word, Button view){
        this.context=context;
        this.text=text;
        this.word=word;
        this.view=view;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(logTAG,word.getKz());

                setButtonState();
                context.onWordClicked(CardWord.this);
            }
        });
        setText();
    }

    public Word getWord() {
        return word;
    }

    private void setText(){
        view.setText(text);
    }
    public void setButtonState(){
        isEnabled=!isEnabled;
        if (isEnabled){
            view.setBackground(context.getContext().getDrawable(R.drawable.my_word_button_focused));
        }else{
            view.setBackground(context.getContext().getDrawable(R.drawable.my_word_button_inactive));
        }
    }

    public Button getView() {
        return view;
    }
}
