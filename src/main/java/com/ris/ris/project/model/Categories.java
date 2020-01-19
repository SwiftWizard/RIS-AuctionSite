package com.ris.ris.project.model;

/*Enums require no JPA annotations*/
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
        String res = "";
        String[] tokens = this.toString().split("_");
        for(int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].charAt(0) + tokens[i].substring(1,tokens[i].length()).toLowerCase();
            res += tokens[i];
        }
        return res;
    }
}
