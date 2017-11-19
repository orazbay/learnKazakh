package kz.learn.orazbay.main.wordsByCategories.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.ArrayList;
import java.util.Arrays;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.data.ControllerWords;
import kz.learn.orazbay.main.wordsByCategories.words.test.TestFragment;
import kz.learn.orazbay.models.Word;
import kz.learn.orazbay.utils.Functions;

/**
 * Created by orazbay on 10/29/17.
 */

public class WordsFragment extends MyAbstractFragment {
    private SwipePlaceHolderView mSwipeView;
    private String category;
    private ArrayList<Object> words;
    public void setCategory(String category){
        this.category=category;
    }
    public WordsFragment(){
        super(WordsFragment.class.getName(),R.layout.fragment_words1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        findViews();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));
        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                Log.e("onItemRemoved"+count,mSwipeView.getAllResolvers().size()+"");
                if (count==0){
                    TestFragment testFragment=new TestFragment();
                    Bundle bundle=new Bundle();
                    bundle.putInt(TestFragment.WORDS_TYPE,TestFragment.WORDS_BY_CATEGORY);
                    bundle.putString("category",category);
                    testFragment.setWords(words);
                    testFragment.setArguments(bundle);
                    Functions.RemoveOldFragmentAndReplace(context,WordsFragment.class.getName(),testFragment);
//                    Functions.ReplaceFragmentWithStack(context,testFragment);
//                    Functions.RemoveFragment(context,WordsFragment.this);
//                    Functions.AddFragment(context,testFragment);
                }
            }
        });
        words= ControllerWords.getRandomWords(category,10);
        for (Object wordObject: words){
            mSwipeView.addView(new WordCard(context,(Word)wordObject,mSwipeView));
        }



        return view;
    }

    @Override
    public void findViews() {
        mSwipeView = view.findViewById(R.id.swipeView);
    }
}
