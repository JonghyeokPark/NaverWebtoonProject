package com.skku.ntoon.ntoon.finishtoon;

/**
 * Created by luusoo on 2016-01-09.
 */
public class FinishData {
    String name, author, date, star, thumbnail, intro, wid;

    public FinishData(String name, String author, String star, String thumbnail, String intro, String wid){
        this.name = name;
        this.author = author;
        this.star = star;
        this.thumbnail = thumbnail;
        this.intro = intro;
        this.wid = wid;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public String getDate(){
        return date;
    }

    public String getStar(){
        return star;
    }

    public String getThumbnail(){
        return thumbnail;
    }

    public String getIntro(){return intro;}

    public String getWid(){return wid;}


}
