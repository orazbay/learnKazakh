package kz.learn.orazbay.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by orazbay on 10/28/17.
 */

public class Word extends RealmObject{
    @PrimaryKey
    private long id;
    private String kz;
    private String rus;
    private String category;
    private boolean isInHaveToLearn=false;

    public Word(){}
    public Word(long id,String kz,String rus,String category){
        this.id=id;
        this.kz=kz;
        this.rus=rus;
        this.category=category;
    }


    //getters
    public long getId() {
        return id;
    }

    public String getKz() {
        return kz;
    }

    public String getRus() {
        return rus;
    }

    public String getCategory() {
        return category;
    }
    public boolean isInHaveToLearn(){
        return isInHaveToLearn;
    }

    //setters
    public void setKz(String kz) {
        this.kz = kz;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setInHaveToLearn(final boolean inHaveToLearn) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                isInHaveToLearn = inHaveToLearn;
            }
});

    }

    @Override
    public String toString() {
        return String.format("id:%s,kz:%s,rus:%s,category:%s,isInHaveToLearn:%b",id,kz,rus,category,isInHaveToLearn);
    }
}
