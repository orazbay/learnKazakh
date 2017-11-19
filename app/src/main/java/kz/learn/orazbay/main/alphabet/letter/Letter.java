package kz.learn.orazbay.main.alphabet.letter;

import java.util.ArrayList;
import java.util.Arrays;

import kz.learn.orazbay.R;

/**
 * Created by orazbay on 11/19/17.
 */

public class Letter {
    private String lat,cyr;
    private int soundId,color;
    public Letter(String lat,String cyr,int soundId,int color){
        this.lat=lat;
        this.cyr=cyr;
        this.soundId=soundId;
        this.color=color;
    }
    public String getCyr() {
        return cyr;
    }

    public String getLat() {
        return lat;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSoundId() {
        return soundId;
    }
    public static  ArrayList<Letter> letters=new ArrayList<>(Arrays.asList(new Letter[]{
            new Letter("Aa","Аа", R.raw.letter1,0xffe57373),
            new Letter("A'a'","Әә", R.raw.letter2,0xfff06292),
            new Letter("Bb","Бб", R.raw.letter3,0xffba68c8),
            new Letter("Dd","Дд", R.raw.letter4,0xff9575cd),
            new Letter("Ee","Ee", R.raw.letter5,0xff7986cb),
            new Letter("Ff","Фф", R.raw.letter6,0xff64b5f6),
            new Letter("Gg","Гг", R.raw.letter7,0xff4fc3f7),
            new Letter("G'g'","Ғғ", R.raw.letter8,0xff4dd0e1),
            new Letter("Hh","x/h", R.raw.letter9,0xff4db6ac),
            new Letter("Ii","Ii", R.raw.letter11,0xff81c784),//auistir sosin
            new Letter("I'i'","и/й", R.raw.letter11,0xffaed581),
            new Letter("Jj","Жж", R.raw.letter12,0xffff8a65),
            new Letter("Kk","Кк", R.raw.letter13,0xffd4e157),
            new Letter("Ll","Лл", R.raw.letter14,0xffffd54f),
            new Letter("Mm","Мм", R.raw.letter15,0xffffb74d),
            new Letter("Nn","Нн", R.raw.letter16,0xffa1887f),
            new Letter("N'n'","Ңң", R.raw.letter17,0xff90a4ae),
            new Letter("Oo","Oo", R.raw.letter18,0xffe57373),
            new Letter("O'o'","Өө", R.raw.letter19,0xfff06292),
            new Letter("Pp","Пп", R.raw.letter20,0xff9575cd),
            new Letter("Qq","Ққ", R.raw.letter21,0xff64b5f6),
            new Letter("Rr","Рр", R.raw.letter22,0xff4fc3f7),
            new Letter("Ss","Сс", R.raw.letter23,0xff4dd0e1),
            new Letter("S's'","Шш", R.raw.letter24,0xff4db6ac),
            new Letter("C'c'","Чч", R.raw.letter25,0xff81c784),
            new Letter("Tt","Тт", R.raw.letter26,0xffff8a65),
            new Letter("Uu","Ұұ", R.raw.letter27,0xffaed581),
            new Letter("U'u'","Үү", R.raw.letter28,0xffd4e157),
            new Letter("Vv","Вв", R.raw.letter29,0xffffd54f),
            new Letter("Yy","Ыы", R.raw.letter3,0xffffb74d),//it also
            new Letter("Y'y'","Уу", R.raw.letter31,0xffa1887f),
            new Letter("Zz","Зз", R.raw.letter32,0xff90a4ae)

    }));
}
