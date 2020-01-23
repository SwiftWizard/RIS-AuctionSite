package com.ris.ris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.persistence.Entity;

@SpringBootApplication()
public class RisAuctionSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RisAuctionSiteApplication.class, args);
    }

}
