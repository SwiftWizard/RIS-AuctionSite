package com.ris.ris.project.service;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements  ImageService{
    @Autowired
    AuctionRepository ar;

    @Override
    public void saveImageFilesForAuction(Long auctionID, MultipartFile ... files) {
        try{
            Auction auction = ar.findById(auctionID).get();
            int numberOfFiles = 0;
            for(MultipartFile file : files){
                numberOfFiles++;

                Byte[] bytes = new Byte[file.getBytes().length];

                int i = 0;
                for(byte b : file.getBytes()){
                    bytes[i++] = b;
                }
                i=0;

                switch (numberOfFiles){
                    case 1:
                        auction.setImageA(bytes);
                        break;
                    case 2:
                        auction.setImageB(bytes);
                        break;
                    case 3:
                        auction.setImageC(bytes);
                        break;
                    default:
                        System.out.println("Too many image files submited!");
                }
            }
            //And re-save auction
            ar.save(auction);
        }catch (IOException e){
            System.err.println("Image upload from auction failed :(\n" + e);
        }
    }

    @Override
    public void saveUserProfileImageFile(Long userID, MultipartFile file) {
        //TODO implement...
        System.err.println("Not implemented!"); //I know i should print this to log...
    }
}
