package com.ren.renzen.Services;

import com.ren.renzen.DomainObjects.ImageDO;
import com.ren.renzen.Repositories.ImageRepository;
import com.ren.renzen.Services.Interfaces.ImageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void save(ImageDO imageDO) {
        imageRepository.save(imageDO);
    }

    @Override
    public Optional<ImageDO> getImage(String id) {
        return imageRepository.findById(id);
    }
}
