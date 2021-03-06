package com.ris.ris.project.model;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*Note: first two characters of zip code should represent country by the "ISO Alpha-2" standard*/
/*Enums require no JPA annotations.. there not persisted in the database...*/
public enum PostalCode {
    RS11000,
    RS21000,
    RS24000,
    HR10000,
    HR20000,
    HU1007;

    /*Method could use a better implementation... */
    public static List<PostalCode> getPostalCodesFromCountry(String countryPrefix){
        return Arrays.stream(PostalCode.values())
                .filter(z -> z.toString().startsWith(countryPrefix))
                .collect(Collectors.toList());
    }
}
