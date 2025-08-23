package com.simpledev.dreamshops.service.image;

import com.simpledev.dreamshops.dto.ImageDto;
import com.simpledev.dreamshops.exceptions.ResourceNotFoundException;
import com.simpledev.dreamshops.model.Image;
import com.simpledev.dreamshops.model.Product;
import com.simpledev.dreamshops.repository.ImageRespository;
import com.simpledev.dreamshops.repository.ProductRepository;
import com.simpledev.dreamshops.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {

    @Autowired
    private ImageRespository imageRespository;

    @Autowired
    private ProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No image found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRespository.findById(id).ifPresentOrElse(imageRespository::delete, () -> {
            throw new ResourceNotFoundException("No image found with id: " + id);
        });
    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setBlob(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download/";

                String downloadUrl = buildDownloadUrl + image.getProduct().getId();
                image.setDownloadUrl(downloadUrl);

                Image savedImage = imageRespository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());

                imageRespository.save(savedImage);


                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());

                savedImageDto.add(imageDto);

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setBlob(new SerialBlob(file.getBytes()));
            imageRespository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
