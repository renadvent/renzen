package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.CommunityDTOs;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//public class CommunityDO_to_CommunityStreamComponentCO implements Converter<CommunityDO, CommunityInfoComponentCO> {

public class CommunityDO_to_CommunityStreamComponentCO
        extends DOMAIN_VIEW_CONVERTER_SUPPORT<CommunityDO, CommunityDTOs.CommunityInfoComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO testConverter;
    final ProfileDO_to_ProfileStreamComponentCO ProfileConverter;

    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    @Autowired
    public CommunityDO_to_CommunityStreamComponentCO(ArticleDO_to_ArticleStreamComponentCO testConverter, ProfileDO_to_ProfileStreamComponentCO profileConverter, UserService userService, ArticleService articleService, CommunityService communityService) {
        this.testConverter = testConverter;
        ProfileConverter = profileConverter;
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
    }

    @Override
    public CommunityDTOs.CommunityInfoComponentCO convertDomainToPublicView(CommunityDO source) {
        CommunityDTOs.CommunityInfoComponentCO co = new CommunityDTOs.CommunityInfoComponentCO();

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setName(source.getName());

        //does not return ProfileStreamComponentCOList or ArticleStreamComponentCOList
        //to cut down on database searches and unused data by client

        return co;
    }

    @Override
    public CommunityDTOs.CommunityInfoComponentCO convertDomainToFullView(CommunityDO source) {
        CommunityDTOs.CommunityInfoComponentCO co = new CommunityDTOs.CommunityInfoComponentCO();

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setName(source.getName());

        //does not return ProfileStreamComponentCOList or ArticleStreamComponentCOList
        //to cut down on database searches and unused data by client

        return co;
    }
}
