package kz.learn.orazbay.data;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmResults;
import kz.learn.orazbay.models.Word;

/**
 * Created by orazbay on 10/29/17.
 */

    public class ControllerWords {
        public static String IS_WORDS_INSERTED="IS_WORDS_INSERTED";
    public static RealmResults<Word> getWordsByCategory(String category){
        return Realm.getDefaultInstance().where(Word.class).equalTo("category",category).findAll();
    }
    public static RealmResults<Word> getWordsHaveToLearn(){
        return Realm.getDefaultInstance().where(Word.class).equalTo("isInHaveToLearn",true).findAll();
    }
    public static ArrayList<Object> getRandomWords(@Nullable String category,int limit){
        ArrayList<Object> words;
        if (category==null){
            words=new ArrayList<>(Arrays.asList(ControllerWords.getAll().toArray()));
        }else {
            words=new ArrayList<>(Arrays.asList(ControllerWords.getWordsByCategory(category).toArray()));
        }
        Collections.shuffle(words);
        return new ArrayList<>(words.subList(0,limit));
    }
    public static RealmResults<Word> getAll(){
        return Realm.getDefaultInstance().where(Word.class).findAll();
    }
    public  static void insertWords(Context context){
        final ArrayList<Word> words=new ArrayList<>();



        String root="words";
        BufferedReader reader = null;
        int i=0;
        for (String category:listAssetFiles(context,root)){
            try {
                reader = new BufferedReader(
                        new InputStreamReader(context.getAssets().open(root+"/"+category)));
                Log.e("category",root+"/"+category);
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    String [] line=mLine.split(" - ");
                    String kz=line[1].trim();
                    String rus=line[0].trim();

                    words.add(new Word(i++,kz,rus,category));

                    Log.e(category,String.format("kaz: %s rus: %s",kz,rus));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(words);
            }
        });
    }
    public static String [] listAssetFiles(Context context,String path) {
        String [] list={};
        try {
            list = context.getAssets().list(path);
            return list;
        } catch (IOException e) {
            return list;
        }
    }
}
