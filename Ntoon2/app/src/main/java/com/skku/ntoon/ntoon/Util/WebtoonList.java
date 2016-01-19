package com.skku.ntoon.ntoon.Util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by luusoo on 2016-01-19.
 */
public class WebtoonList {
    static ArrayList<HashMap<String,String>> webtoonList;

    public WebtoonList(ArrayList<HashMap<String, String>> webtoonList) {
        this.webtoonList = webtoonList;
    }

    public WebtoonList(){
    }

    public static ArrayList<HashMap<String,String>> getWebtoonList() {
        return webtoonList;
    }
}
