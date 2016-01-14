package com.skku.ntoon.ntoon.finishtoon;

/**
 * Created by luusoo on 2016-01-09.
 */
public class FinishData {
    String name, author, date, star;

    public FinishData(String name, String author, String star){
        this.name = name;
        this.author = author;
        this.star = star;
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
}
