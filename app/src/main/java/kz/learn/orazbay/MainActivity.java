package kz.learn.orazbay;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.Arrays;

import kz.learn.orazbay.data.ControllerWords;
import kz.learn.orazbay.main.MainPageFragment;
import kz.learn.orazbay.main.wordsByCategories.words.test.TestFragment;
import kz.learn.orazbay.utils.Functions;

public class MainActivity extends AppCompatActivity {
    private final String logTAG=MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        Intent intent1=new Intent(this,SplashActivity.class);
        startActivity(intent1);
        if (intent!=null) {
            if (intent.getIntExtra("FLAG_NOTIFY_LEARN", -1) == NotificationUtils.FLAG_NOTIFY_LEARN) {
                TestFragment testFragment = new TestFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(TestFragment.WORDS_TYPE, TestFragment.WORDS_TO_LEARN);
                testFragment.setArguments(bundle);
                Functions.ReplaceFragmentWithStack(this, testFragment);
            }else {
                Functions.ReplaceFragmentWithStack(this,new MainPageFragment());
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
