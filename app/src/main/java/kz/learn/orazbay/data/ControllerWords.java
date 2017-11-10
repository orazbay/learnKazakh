package kz.learn.orazbay.data;

import java.util.ArrayList;

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
    public static RealmResults<Word> getAll(){
        return Realm.getDefaultInstance().where(Word.class).findAll();
    }
    public  static void insertWords(){
        final ArrayList<Word> words=new ArrayList<>();
        Word word=new Word(0,"Ана","Мама","отбасы");
        word.setInHaveToLearn(true);
        words.add(word);
        Word word1=new Word(1,"Аке","Папа","отбасы");
        words.add(word1);
        Word word2=new Word(2,"Апа","Сестра","отбасы");
        words.add(word2);
        Word word3=new Word(3,"Ини","Братишка","отбасы");
        words.add(word3);
        Word word4=new Word(4,"Ага","Брат","отбасы");
        words.add(word4);
        Word word5=new Word(5,"Ата","Дедушка","отбасы");
        words.add(word5);
        Word word6=new Word(6,"Аже","Бабушка","отбасы");
        words.add(word6);
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(words);
            }
        });
    }
}
