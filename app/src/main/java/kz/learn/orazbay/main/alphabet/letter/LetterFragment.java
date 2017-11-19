package kz.learn.orazbay.main.alphabet.letter;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;

/**
 * Created by orazbay on 11/18/17.
 */

public class LetterFragment extends MyAbstractFragment implements View.OnClickListener {
    private int currentLetter=0;

    VerticalInfiniteCycleViewPager verticalViewPager;
    VerticalPagerAdapter verticalPagerAdapter;
    ImageView speakerImageView;
    private MediaPlayer mediaPlayer;
    public LetterFragment(){
        super(LetterFragment.class.getName(), R.layout.fragment_letter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        findViews();
        mediaPlayer=new MediaPlayer();
        verticalPagerAdapter=new VerticalPagerAdapter(context,this);
        verticalViewPager.setAdapter(verticalPagerAdapter);
        verticalViewPager.setCurrentItem(currentLetter);
        verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mediaPlayer.seekTo(mediaPlayer.getDuration());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mediaPlayer.release();
    }
    public void playAudio(int index, final View view) {
        mediaPlayer = MediaPlayer.create(context, Letter.letters.get(index).getSoundId());
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                setSpeakerImage(false,view);
            }
        });
        mediaPlayer.start(); // starting mediaplayer
    }

    public void setCurrentLetter(int currentLetter) {
        this.currentLetter = currentLetter;
    }

    @Override
    public void findViews() {
        verticalViewPager=view.findViewById(R.id.verticalViewPager);
        speakerImageView=view.findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View view) {
        playAudio(verticalViewPager.getRealItem(),view);
        setSpeakerImage(true,view);
    }
    private void setSpeakerImage(boolean with,View view){
        if (view!=null){
            ImageView imageView=(ImageView) view;
            if (with){
                imageView.setImageResource(R.drawable.ic_speaker);
            }else {
                imageView.setImageResource(R.drawable.ic_speaker_without);
            }
        }
    }
}
