package com.el.edp.util;

import com.el.edp.sfs.domain.EdpSfsImgScale;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 图片处理
 *
 * @author yangzhibin
 * @since 2017/10/23
 */
@Slf4j
public abstract class EdpImageUtil {

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     *
     * @param inputImageFile  Input stream of the original image
     * @param outputImageFile Output stream to save the resized image
     * @param scaledWidth     Absolute width in pixels
     * @param scaledHeight    Absolute height in pixels
     * @throws IOException when processing failed
     */
    public static void resize(
        InputStream inputImageFile, OutputStream outputImageFile,
        int scaledWidth, int scaledHeight) throws IOException {

        // reads input image
        BufferedImage inputImage = ImageIO.read(inputImageFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        //String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, "jpg", outputImageFile);
    }

    /**
     * Resizes an image to a square with specified absolute size.
     *
     * @param originalImagePath Path of the original image
     * @param resizedImagePath  Path to save the resized image
     * @param size              Absolute width/height in pixels
     * @throws IOException when processing failed
     */
    public static void resizeImage(Path originalImagePath, Path resizedImagePath, int size) throws IOException {
        resizeImage(originalImagePath, resizedImagePath, size, size);
    }

    /**
     * Resizes an image to a square with specified absolute size.
     *
     * @param originalImagePath Path of the original image
     * @param resizedImagePath  Path to save the resized image
     * @param width             Absolute width in pixels
     * @param height            Absolute height in pixels
     * @throws IOException when processing failed
     */
    public static void resizeImage(Path originalImagePath, Path resizedImagePath, int width, int height) throws IOException {
        try (val inputStream = Files.newInputStream(originalImagePath, StandardOpenOption.READ);
             val outputStream = Files.newOutputStream(resizedImagePath)) {
            resize(inputStream, outputStream, width, height);
        }
    }

    /**
     * 图片缩放处理
     *
     * @param image       图片
     * @param scale       图片缩放设置
     * @param scaledImage 缩放输出
     */
    public static void scale(BufferedImage image, EdpSfsImgScale scale, OutputStream scaledImage) throws IOException {

        // creates output image
        val outputImage = new BufferedImage(scale.getScaleW(), scale.getScaleH(), image.getType());

        // scales the input image to the output image
        val g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, scale.getScaleW(), scale.getScaleH(), null);
        g2d.dispose();

        // writes to output file
        ImageIO.write(outputImage, "jpg", scaledImage);
    }

}
