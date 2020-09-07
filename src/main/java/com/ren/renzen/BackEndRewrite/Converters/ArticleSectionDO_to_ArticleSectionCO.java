package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.ContentCOs.ArticleSectionCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import com.mongodb.lang.Nullable;
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
