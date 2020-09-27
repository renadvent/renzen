package com.ren.renzen.Services.Interfaces;

import com.ren.renzen.DomainObjects.ImageDO;

import java.util.Optional;

public interface ImageService {

    void save(ImageDO imageDO);

    Optional<ImageDO> getImage(String id);
    public String generateSAS(String name);

}
