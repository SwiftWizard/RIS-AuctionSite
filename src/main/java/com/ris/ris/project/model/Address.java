package com.ris.ris.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;

    @NotNull(message = "Please select a country")
    @Enumerated(value = EnumType.STRING)
    private Country country;

    @NotNull(message = "Please select a postal code")
    @Enumerated(value = EnumType.STRING)
    private PostalCode postalCode;

    @NotNull(message = "Please add a city")
    private String city;

    @NotNull(message = "Please add a address")
    private String address;

    public Address() {
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}


