package com.ren.renzen.Converters;

import com.mongodb.lang.Nullable;
import com.ren.renzen.CommandObjects.ArticleDTOs;
import com.ren.renzen.DomainObjects.ArticleSectionDO;
import com.ren.renzen.Services.Interfaces.ImageService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ArticleSectionDO_to_ArticleSectionCO implements Converter<ArticleSectionDO, ArticleDTOs.ArticleSectionCO> {

    final ImageService imageService;

    public ArticleSectionDO_to_ArticleSectionCO(ImageService imageService) {
        this.imageService = imageService;
    }

    @Synchronized
    @Nullable
    @Override
    public ArticleDTOs.ArticleSectionCO convert(ArticleSectionDO source) {
        ArticleDTOs.ArticleSectionCO co = new ArticleDTOs.ArticleSectionCO();
        co.setHeader(source.getHeader());
        co.setBody(source.getBody());

        try {
            String name = source.getImageID().substring(source.getImageID().lastIndexOf('/') + 1);

            co.setImageID(imageService.generateSAS(name));
        } catch (Exception e) {
            co.setImageID(null);
        }


        return co;
    }
}
