package kz.learn.orazbay.main.alphabet.letter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kz.learn.orazbay.R;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class VerticalPagerAdapter extends PagerAdapter {


    private LayoutInflater mLayoutInflater;
    private View.OnClickListener onClickListener;

    public VerticalPagerAdapter(final Context context, View.OnClickListener onClickListener) {
        mLayoutInflater = LayoutInflater.from(context);
        this.onClickListener=onClickListener;
    }


    @Override
    public int getCount() {
        return Letter.letters.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.letter_item, container, false);

        view.setTransitionName("letter"+position);


        view.findViewById(R.id.imageView).setOnClickListener(onClickListener);

        CardView cardView=view.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(Letter.letters.get(position).getColor());



        TextView lat,cyr;
        lat=view.findViewById(R.id.lat);
        cyr=view.findViewById(R.id.cyr);

        lat.setText(Letter.letters.get(position).getLat());
        cyr.setText(Letter.letters.get(position).getCyr());

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