package com.skku.ntoon.ntoon.Util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by luusoo on 2016-01-19.
 */
public class WebtoonList {
    static ArrayList<HashMap<String,String>> webtoonList = new ArrayList<>();
    static ArrayList<HashMap<String,String>> wList= new ArrayList<>();

    public void setWebtoonList(ArrayList<HashMap<String, String>> webtoonList) {
        this.webtoonList = webtoonList;
    }

    public void setwList(ArrayList<HashMap<String, String>> wList) {
        this.wList = wList;
    }

    public ArrayList<HashMap<String,String>> getWebtoonList() {
        return webtoonList;
    }

    public ArrayList<HashMap<String,String>> getwList() {
        return wList;
    }

    public void setOrder(int t,String type) {
        if(t == 0) {
            for (int i = 0; i < webtoonList.size(); i++) {
                for (int j = 1; j < i; j++) {
                    if (webtoonList.get(j).get(type).compareTo(webtoonList.get(j - 1).get(type)) == 1) {
                        HashMap<String, String> temp = webtoonList.get(j);
                        webtoonList.set(j, webtoonList.get(j - 1));
                        webtoonList.set(j - 1, temp);
                    }
                }
            }
        }
        else if(t == 1){
            for (int i = 0; i < wList.size(); i++) {
                for (int j = 1; j < i; j++) {
                    if (wList.get(j).get(type).compareTo(wList.get(j - 1).get(type)) == 1) {
                        HashMap<String, String> temp = wList.get(j);
                        wList.set(j, wList.get(j - 1));
                        wList.set(j - 1, temp);
                    }
                }
            }
        }
    }

    public boolean isEmpty(int i) {
        if(i == 0){
            if(webtoonList.size() == 0) {
                return true;
            }
        }
        else {
            if(wList.size() == 0){
                return true;
            }
        }
        return false;
    }
}
