package kz.learn.orazbay.main.wordsByCategories.words;

import android.content.Context;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import kz.learn.orazbay.R;
import kz.learn.orazbay.models.Word;

/**
 * Created by orazbay on 10/29/17.
 */

@Layout(R.layout.word_item)
public class WordCard {

    @View(R.id.kz)
    private TextView kzTV;
    @View(R.id.rus)
    private TextView rusTV;
    @View(R.id.pin)
    private LikeButton isInHaveToLearnSwitch;

    private Word word;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public WordCard(Context context, Word word, SwipePlaceHolderView swipeView) {
        mContext = context;
        this.word = word;
        mSwipeView = swipeView;
    }
    @Resolve
    private void onResolved(){
        kzTV.setText(word.getKz());
        rusTV.setText(word.getRus());
        isInHaveToLearnSwitch.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                updateWord(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                updateWord(false);
            }
        });
        isInHaveToLearnSwitch.setLiked(word.isInHaveToLearn());
    }

    @SwipeOut
    private void onSwipedOut(){
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
    }

    @SwipeIn
    private void onSwipeIn(){

    }

    @SwipeInState
    private void onSwipeInState(){
    }

    @SwipeOutState
    private void onSwipeOutState(){
    }
    private void updateWord(boolean add){
        word.setInHaveToLearn(add);
    }
}