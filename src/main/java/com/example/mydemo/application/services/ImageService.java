package com.example.mydemo.application.services;

import com.example.mydemo.application.dtos.ImageUploadResponse;
import com.example.mydemo.persistance.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageUploadResponse uploadImage(MultipartFile image) throws IOException;
    Image getInfoByImageByName(String name);
    byte[] getImage(String name);
}
