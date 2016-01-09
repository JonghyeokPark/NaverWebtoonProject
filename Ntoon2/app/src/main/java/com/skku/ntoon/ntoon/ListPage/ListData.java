package com.skku.ntoon.ntoon.ListPage;

/**
 * Created by luusoo on 2016-01-09.
 */
public class ListData {
    String name, author, date;
    double star;

    public ListData(String name, String author, String date, double star){
        this.name = name;
        this.author = author;
        this.date = date;
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

    public double getStar(){
        return star;
    }
}
