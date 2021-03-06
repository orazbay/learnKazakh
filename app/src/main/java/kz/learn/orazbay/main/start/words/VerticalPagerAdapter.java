package kz.learn.orazbay.main.start.words;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;
import kz.learn.orazbay.R;
import kz.learn.orazbay.data.ControllerWords;
import kz.learn.orazbay.models.Word;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class VerticalPagerAdapter extends PagerAdapter {
    RealmResults<Word> words;
    private LayoutInflater mLayoutInflater;

    public VerticalPagerAdapter(final Context context) {
        words= ControllerWords.getWordsByCategory("отбасы");
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.word_item_one_way, container, false);

        ((TextView)view.findViewById(R.id.txt_item)).setText(words.get(position).getKz());
        HorizontalPagerAdapter.counter++;

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
