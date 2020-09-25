package com.ren.renzen.Converters;

import com.mongodb.lang.Nullable;
import com.ren.renzen.CommandObjects.ArticleSectionCO;
import com.ren.renzen.DomainObjects.ArticleSectionDO;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleSectionDO_to_ArticleSectionCO implements Converter<ArticleSectionDO, ArticleSectionCO> {
    @Synchronized
    @Nullable
    @Override
    public ArticleSectionCO convert(ArticleSectionDO source) {
        ArticleSectionCO co = new ArticleSectionCO();
        co.setHeader(source.getHeader());
        co.setBody(source.getBody());
        return co;
    }
}
