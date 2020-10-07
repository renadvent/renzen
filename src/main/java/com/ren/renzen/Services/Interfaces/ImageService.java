package com.ren.renzen.Services.Interfaces;

import java.util.Optional;

public interface ImageService {

    void save(ImageDO imageDO);

    Optional<ImageDO> getImage(String id);
    public String generateSAS(String name);

}
