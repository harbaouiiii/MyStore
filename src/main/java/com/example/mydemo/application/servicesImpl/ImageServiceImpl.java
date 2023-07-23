package com.example.mydemo.application.servicesImpl;

import com.example.mydemo.application.dtos.ImageUploadResponse;
import com.example.mydemo.application.services.ImageService;
import com.example.mydemo.persistance.entities.Image;
import com.example.mydemo.persistance.repositories.ImageRepository;
import com.example.mydemo.utils.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {
       imageRepository.save(
                Image.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtil.compressImage(file.getBytes())).build()
        );
       return new ImageUploadResponse("Image uploaded successfully: "+file.getOriginalFilename());
    }

    @Transactional
    @Override
    public Image getInfoByImageByName(String name) {
        Optional<Image> dbImage = imageRepository.findByName(name);
        return dbImage.map(image -> Image.builder()
                .name(image.getName())
                .type(image.getType())
                .imageData(ImageUtil.decompressImage(image.getImageData()))
                .build()).orElseThrow(() -> new RuntimeException("Image not found"));
    }

    @Transactional
    @Override
    public byte[] getImage(String name) {
        Optional<Image> dbImage = imageRepository.findByName(name);
        if (dbImage.isEmpty()) {
            throw new RuntimeException("Image not found");
        }
        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }
}
