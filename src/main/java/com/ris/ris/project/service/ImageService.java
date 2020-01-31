package com.ris.ris.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFilesForAuction(Long auctionID, MultipartFile... files);

    void saveUserProfileImageFile(Long userID, MultipartFile file);
}
