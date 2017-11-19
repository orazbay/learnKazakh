package kz.learn.orazbay.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.main.alphabet.AlphabetFragment;
import kz.learn.orazbay.main.settings.SettingsFragment;
import kz.learn.orazbay.main.start.StartFragment;
import kz.learn.orazbay.main.wordsByCategories.CategoryFragment;

/**
 * Created by orazbay on 10/29/17.
 */

public class MainPageFragment extends MyAbstractFragment {
    private ViewPager viewPager;
    private AHBottomNavigation ahBottomNavigation;

    private Fragment[] fragments={
            new StartFragment(),
            new AlphabetFragment(),
            new CategoryFragment(),
            new SettingsFragment()
    };

    public MainPageFragment() {
        super(MainPageFragment.class.getName(),R.layout.fragment_main_page);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_main_page,container,false);
        findViews();
        setupViewPager();
        setupBottomNavigation();
        return view;
    }

    @Override
    public void findViews() {
        viewPager=view.findViewById(R.id.viewPager);
        ahBottomNavigation=view.findViewById(R.id.bottom_navigation);
    }
    private void setupBottomNavigation(){
        AHBottomNavigationItem startItem = new AHBottomNavigationItem(getString(R.string.start), R.drawable.ic_airplanemode_active_black_24dp);
        AHBottomNavigationItem alphabetItem = new AHBottomNavigationItem(getString(R.string.alphabet), R.drawable.ic_sort_by_alpha_black_24dp);
        AHBottomNavigationItem categoriesItem = new AHBottomNavigationItem(getString(R.string.categories), R.drawable.ic_spellcheck_black_24dp);
        AHBottomNavigationItem settingsItem = new AHBottomNavigationItem(getString(R.string.settings), R.drawable.ic_settings_black_24dp);


        ahBottomNavigation.addItem(startItem);
        ahBottomNavigation.addItem(alphabetItem);
        ahBottomNavigation.addItem(categoriesItem);
        ahBottomNavigation.addItem(settingsItem);


        ahBottomNavigation.setAccentColor(ContextCompat.getColor(context,R.color.colorPrimary));
        ahBottomNavigation.setInactiveColor(ContextCompat.getColor(context,R.color.light_gray));


        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position);
                return true;
            }
        });

    }
    private void setupViewPager(){
        ViewPagerAdapter adapter=new ViewPagerAdapter(getChildFragmentManager(),fragments);
//        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(logTAG,"onPageSelected:"+position);
                ahBottomNavigation.setCurrentItem(position);
                ((AnimationInitter)fragments[position]).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);
    }
}
