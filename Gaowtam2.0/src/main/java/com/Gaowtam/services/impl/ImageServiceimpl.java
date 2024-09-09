package com.Gaowtam.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Gaowtam.helpers.AppConstats;
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
    public String uploadImage(MultipartFile contactImage,String filename) {
        // aikhne oi code lekha lagbe jekhane image upload hobe server er moddhe

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];/*
                                                                               * Jotto data ace toto byte er image size
                                                                               * jabe
                                                                               */
            contactImage.getInputStream().read(data);///data er moddhe image er data add hoice

            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id",filename));////coludinary te image er data upload hoyece mane image upload hoyece

            return this.getUrlFromPublicId(filename);//coludinary thake image er size nia ashe url dicce;

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
                .url()
                .transformation(
                        new Transformation<>()
                                // .width(500)// image er width
                                // .height(500)// image er height
                                // .crop("fill"))
                                .width(AppConstats.CONTACT_IMAGE_WIDTH)
                                .height(AppConstats.CONTACT_IMAGE_HEIGHT)
                                .crop(AppConstats.CONTACT_IMAGE_CROP))
                .generate(publicId);
    }

}
