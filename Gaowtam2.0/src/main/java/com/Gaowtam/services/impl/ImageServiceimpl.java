package com.Gaowtam.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Gaowtam.services.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageServiceimpl implements ImageService {

    private Cloudinary cloudinary;

    // aikahen autowired na kore constrauctor add korce 2ty kora jai
    public ImageServiceimpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage) {
        // aikhne oi code lekha lagbe jekhane image upload hobe server er moddhe

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];/* Jotto data ace toto byte er image bane jabe */

            String filename=UUID.randomUUID().toString();
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data,ObjectUtils.asMap(
                "public_id",contactImage.getOriginalFilename()
            ));

        } catch (IOException e) {

            e.printStackTrace();
        } 

        // return this.getUrlFromPublicId(filename);
        return "";
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
       return cloudinary
       .url()
       .transformation(
        new Transformation<>()
        .width(500)
        .height(500)
        .crop("fill")
       )
       .generate(publicId);
    }
    
}
