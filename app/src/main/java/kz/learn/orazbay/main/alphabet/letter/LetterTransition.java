package kz.learn.orazbay.main.alphabet.letter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.TransitionSet;
import android.util.AttributeSet;

/**
 * Transition that performs almost exactly like {@link android.transition.AutoTransition}, but has an
 * added {@link ChangeImageTransform} to support properly scaling up our gorgeous kittens.
 *
 * @author bherbst
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LetterTransition extends TransitionSet {
    public LetterTransition() {
        init();
    }

    /**
     * This constructor allows us to use this transition in XML
     */
    public LetterTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrdering(ORDERING_TOGETHER).
        addTransition(new ChangeBounds()).
                addTransition(new ChangeTransform()).
                addTransition(new Explode()).
                addTransition(new ChangeClipBounds()).
                addTransition(new ChangeImageTransform());
    }
}