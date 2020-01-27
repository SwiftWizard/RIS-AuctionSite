package com.ris.ris.project.controller;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.repository.AuctionRepository;
import com.ris.ris.project.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/images")
public class ImageController {
    /*Some truly horrible stuff :( */
    @Autowired
    AuctionRepository ar;

    @Autowired
    UserRepository ur;

    @GetMapping("/auction?auctionID={auctionID}&image={specificImage}")
    public void showAuctionImage(@PathVariable Long auctionID, @PathVariable String specificImage, HttpServletResponse response) throws IOException {
        Auction auction = ar.findById(auctionID).get();
        Byte[] objectByteArray;
        switch (specificImage){
            case "A":
                objectByteArray = auction.getImageA();
            case "B":
                objectByteArray = auction.getImageB();
                break;
            case "C":
                objectByteArray = auction.getImageC();
                break;
            default:
                objectByteArray = new Byte[0];
                System.err.println("Another error yay!");
        }
        System.err.println("\n\n\nImage field:\n" + objectByteArray + "\n\n\n");
        if(objectByteArray != null){
            byte[] primitiveByteArr = new byte[objectByteArray.length];

            int i = 0;
            for(byte b: primitiveByteArr){
                b = objectByteArray[i++];
            }

            InputStream inputStream = new ByteArrayInputStream(primitiveByteArr);
            response.setContentType("image/jpeg");
            IOUtils.copy(inputStream, response.getOutputStream());
            //System.err.println("\n\n\nImage has been fetched\n\n\n");
        }
    }

    @RequestMapping
    public String showUserProfileImage(){
        //TODO implement
        return "";
    }

}
