package com.ris.ris.project.model;

/*Enums require no JPA annotations*/
public enum Country {
    SERBIA("RS"),
    CROATIA("HR"),
    MONTENEGRO("ME"),
    GERMANY("DE"),
    AUSTRIA("AT"),
    HUNGARY("HU"),
    BIH("BA");
    //TODO add all countries


    private String countryISO_Aplpha2;

    private Country(String prefix){
        this.countryISO_Aplpha2 = prefix;
    }

    public String getPrefix(){
        return this.countryISO_Aplpha2;
    }
}
