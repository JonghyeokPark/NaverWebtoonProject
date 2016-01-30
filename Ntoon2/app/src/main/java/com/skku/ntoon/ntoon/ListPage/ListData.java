package com.skku.ntoon.ntoon.ListPage;

/**
 * Created by luusoo on 2016-01-09.
 */
public class ListData {
    String title,update,number,star,hits,thumbnail,image;

    public ListData(String title, String update, String number, String star,
                    String hits, String thumbnail, String image){
        this.title = title;
        this.update = update;
        this.number = number;
        this.star = star;
        this.hits = hits;
        this.thumbnail = thumbnail;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getUpdate() {
        return update;
    }

    public String getNumber() {
        return number;
    }

    public String getHits() {
        return hits;
    }

    public String getStar() {
        return star;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }

}
