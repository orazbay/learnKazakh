package kz.learn.orazbay.main.start.words;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import io.realm.RealmResults;
import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.main.AnimationInitter;
import kz.learn.orazbay.models.Word;

/**
 * Created by orazbay on 10/29/17.
 */

public class WordsFragment extends MyAbstractFragment implements AnimationInitter {
    public WordsFragment(){
        super(WordsFragment.class.getName(),R.layout.fragment_words);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        view=inflater.inflate(R.layout.fragment_words,container,false);
        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager = view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), true));
        return view;
    }

    @Override
    public void findViews() {

    }

    @Override
    public void show() {
        
    }
}
