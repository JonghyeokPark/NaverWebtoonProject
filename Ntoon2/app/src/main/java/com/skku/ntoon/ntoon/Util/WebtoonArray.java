package com.skku.ntoon.ntoon.Util;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by luusoo on 2016-01-19.
 */
public class WebtoonArray extends ArrayList<WebtoonData> {
    public boolean isEmpty (String temp) {
        int i;
        for(i = 0; i < this.size() ; i++){
            if(this.get(i).getName().equals(temp)){
                return false;
            }
        }
        return true;
    }

    public void setOrder(String type) {
        if(type.equals("name")) {
            for (int i = 0; i < this.size(); i++) {
                for (int j = 0; j < this.size()-1-i; j++) {
                    if (this.get(j).getName().compareTo(this.get(j+1).getName()) > 0) {
                        WebtoonData temp = this.get(j);
                        this.set(j,this.get(j+1));
                        this.set(j+1,temp);
                    }
                }
            }
        }
        else if(type.equals("star")) {
            for (int i = 0; i < this.size(); i++) {
                for (int j = 0; j < this.size()-1-i; j++) {
                    if (this.get(j).getStar().compareTo(this.get(j+1).getStar()) == 1) {
                        WebtoonData temp = this.get(j);
                        this.set(j,this.get(j+1));
                        this.set(j+1,temp);
                    }
                }
            }
        }
        else if(type.equals("startdate")) {
            for (int i = 0; i < this.size(); i++) {
                for (int j = 0; j < this.size()-1-i; j++) {
                    if (this.get(j).getDate().compareTo(this.get(j+1).getDate()) == 1) {
                        WebtoonData temp = this.get(j);
                        this.set(j,this.get(j+1));
                        this.set(j+1,temp);
                    }
                }
            }
        }
    }
}
