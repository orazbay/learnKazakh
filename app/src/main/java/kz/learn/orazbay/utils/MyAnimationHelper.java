package kz.learn.orazbay.utils;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
public class MyAnimationHelper {
	private static Animation getAnimByType(Context context,int id)
	{
		return AnimationUtils.loadAnimation(context, id);
	}

	public static void invokeForView(Context context,View v, int animationId, Animation.AnimationListener callback)
	{
		Animation animWithListener = getAnimByType(context,animationId);
		if (callback!=null){
			animWithListener.setAnimationListener(callback);
		}
		v.startAnimation(animWithListener);
	}


	public static void invokeForAllViews(Context context,View[] views, int animationId, Animation.AnimationListener callback)
	{
		Animation animWithListener = getAnimByType(context,animationId);
		if (callback!=null){
			animWithListener.setAnimationListener(callback);
		}
		for (View v:views)
		{
			v.startAnimation(animWithListener);
		}
	}
}


