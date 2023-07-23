package com.example.mydemo.presentation.controllers;

import com.example.mydemo.application.dtos.ImageUploadResponse;
import com.example.mydemo.application.services.ImageService;
import com.example.mydemo.persistance.entities.Image;
import com.example.mydemo.utils.Summary;
import com.example.mydemo.utils.Tags;
import com.example.mydemo.utils.Urls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(Urls.BASE_IMAGE_URL)
@CrossOrigin(origins = Urls.ORIGINS_URL)
@Tag(name = Tags.IMAGE)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    @Operation(summary = Summary.UPLOAD_IMAGE)
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        ImageUploadResponse response = imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/info/{name}")
    @Operation(summary = Summary.GET_IMAGE_INFO_BY_NAME)
    public ResponseEntity<Image>  getImageInfoByName(@PathVariable("name") String name){
        Image image = imageService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/{name}")
    @Operation(summary = Summary.GET_IMAGE_BY_NAME)
    public ResponseEntity<byte[]>  getImageByName(@PathVariable("name") String name){
        byte[] image = imageService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
