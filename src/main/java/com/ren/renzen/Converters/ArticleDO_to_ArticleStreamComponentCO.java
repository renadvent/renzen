package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ArticleStreamComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ArticleDO_to_ArticleStreamComponentCO implements Converter<ArticleDO, ArticleStreamComponentCO> {

    final UserService userService;
    final ArticleService articleService;

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ArticleDO_to_ArticleStreamComponentCO(UserService userService, ArticleService articleService, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        this.userService = userService;
        this.articleService = articleService;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Synchronized
    @Nullable
    @Override
    public ArticleStreamComponentCO convert(ArticleDO source){

        final ArticleStreamComponentCO co = new ArticleStreamComponentCO();

        co.setName(source.getName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setAuthorID(source.getUserID().toHexString());
        co.setAuthorName(userService.findBy_id(source.getUserID()).getUsername());
//        userService.findBy_id(source.getUserID()).ifPresent(user->co.setProfileStreamComponentCO(profileDO_to_profileStreamComponentCO.convert(user)));
        co.setProfileStreamComponentCO(profileDO_to_profileStreamComponentCO.convert(userService.findBy_id(source.getUserID())));

        return co;

    }
}
