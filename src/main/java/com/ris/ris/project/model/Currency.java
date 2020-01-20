package com.ris.ris.project.model;

public enum Currency {
    RSD(Country.SERBIA),
    EURO(Country.MONTENEGRO, Country.GERMANY, Country.AUSTRIA);
    //TODO add more currencies

    private Country[] usedInCountries;

    private Currency(Country ... countries){
        this.usedInCountries = countries;
    }

    public Country[] getCountriesThatUseCurrency(){
        return this.usedInCountries;
    }
}
