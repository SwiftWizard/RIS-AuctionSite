package com.ris.ris.project.model;

import java.util.Arrays;

public enum Categories {
    //TODO add other categories (these where copied from Ebay...)
    COLLECTIBLES_AND_ARTS,
    ELECTRONICS,
    FASHION,
    HOME_AND_GRADEN,
    AUTO_PARTS_AND_ACCESSORIES,
    MUSICAL_INSTRUMENTS_AND_GEAR,
    SPORTING_GOODS,
    TOYS_AND_HOBBIES,
    OTHER_CATEGORIES;

    @Override
    public String toString(){
        String str = this.toString().replace("_", " ").toLowerCase();
        return capitalizeString(str);
    }

    private String capitalizeString(String str){
        if(str == null || str.isEmpty()){
            return str;
        }else{
            String res = "";
            String token[] = str.split(" ");
            for(int i = 0; i < token.length; i++) {
                token[i] = token[i].substring(0,1).toUpperCase() + token[i].substring(1,token.length);
                res += token[i];
            }
            return res;
        }
    }
}
