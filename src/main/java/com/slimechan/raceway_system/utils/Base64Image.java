package com.slimechan.raceway_system.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64Image {

    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            imageString = Base64.getEncoder().encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static String encodeToString(byte[] image, String type){
        ByteArrayInputStream bis = new ByteArrayInputStream(image);
        try {
            BufferedImage bimg = ImageIO.read(bis);
            return encodeToString(bimg, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] decodeToImage(String imageString) {
        byte[] imageByte = new byte[0];
        try {
            imageByte = Base64.getDecoder().decode(imageString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageByte;
    }

}
