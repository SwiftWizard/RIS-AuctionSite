package com.ris.ris.project.model;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*Note: first two characters of zip code should represent country by the "ISO Alpha-2" standard*/
/*Enums require no JPA annotations*/
public enum ZipCode {
    RS11000, RS21000, RS24000;

    public static List<ZipCode> getZipCodesFromCountry(String countryPrefix){
        return Arrays.stream(ZipCode.values())
                .filter(z -> z.toString().startsWith(countryPrefix))
                .collect(Collectors.toList());
    }
}
