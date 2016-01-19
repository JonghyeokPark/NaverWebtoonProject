package com.skku.ntoon.ntoon.Util;

import java.util.ArrayList;

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
}
