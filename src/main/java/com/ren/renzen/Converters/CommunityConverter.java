package com.ren.renzen.Converters;

import com.mongodb.lang.Nullable;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.ModelAssemblers.ArticleAssembler;
import com.ren.renzen.ModelAssemblers.ProfileAssembler;
import com.ren.renzen.ResourceObjects.CommandObjects.CommunityDTOs;
import com.ren.renzen.ResourceObjects.DomainObjects.CommunityDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CommunityConverter {
    @Component
    //public class CommunityDO_to_CommunityStreamComponentCO implements Converter<CommunityDO, CommunityInfoComponentCO> {

    public static class CommunityDO_to_CommunityStreamComponentCO
            extends DOMAIN_VIEW_CONVERTER_SUPPORT<CommunityDO, CommunityDTOs.CommunityInfoComponentCO> {

        final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO testConverter;
        final com.ren.renzen.Converters.ProfileConverter.ProfileDO_to_ProfileStreamComponentCO ProfileConverter;

        final UserService userService;
        final ArticleService articleService;
        final CommunityService communityService;

        @Autowired
        public CommunityDO_to_CommunityStreamComponentCO(ArticleConverter.ArticleDO_to_ArticleStreamComponentCO testConverter, com.ren.renzen.Converters.ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileConverter, UserService userService, ArticleService articleService, CommunityService communityService) {
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

    @Component
    public static class CommunityDO_to_CommunityTabComponentCO
            extends DOMAIN_VIEW_CONVERTER_SUPPORT<CommunityDO, CommunityDTOs.CommunityTabComponentCO> {

        final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
        final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

        final ArticleService articleService;
        final UserService userService;

        final ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler;
        final ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler;

        public CommunityDO_to_CommunityTabComponentCO(ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, ArticleService articleService, UserService userService, ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler, ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler) {
            this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
            this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
            this.articleService = articleService;
            this.userService = userService;
            this.profileStreamCOAssembler = profileStreamCOAssembler;
            this.articleStreamCOAssembler = articleStreamCOAssembler;
        }

        public void common(CommunityDO source, CommunityDTOs.CommunityTabComponentCO co) {


        }

        @Synchronized
        @Nullable
        @Override
        public CommunityDTOs.CommunityTabComponentCO convertDomainToPublicView(CommunityDO source) {

            var co = new CommunityDTOs.CommunityTabComponentCO();

            co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

            if (source.isCommunityIsPublic()) {
                co.set_id(source.get_id().toHexString());
                co.setObjectId(source.get_id());
                co.setName(source.getName());
                co.setArticleInfoComponentCOS(articleStreamCOAssembler
                        .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleDOList())));
                co.setNumberOfArticles(source.getArticleDOList().size());
                co.setProfileInfoComponentCOS(profileStreamCOAssembler
                        .assembleDomainToPublicModelViewCollection(userService.findAllBy_Id(source.getProfileDOList())));
                co.setNumberOfUsers(source.getProfileDOList().size());
            }

            return co;
        }

        @Synchronized
        @Nullable
        @Override
        public CommunityDTOs.CommunityTabComponentCO convertDomainToFullView(CommunityDO source) {

            var co = new CommunityDTOs.CommunityTabComponentCO();
            co.setACCESS_TYPE(ACCESS_TYPE_FULL);

            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());
            co.setName(source.getName());
            co.setArticleInfoComponentCOS(articleStreamCOAssembler
                    .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleDOList())));
            co.setNumberOfArticles(source.getArticleDOList().size());
            co.setProfileInfoComponentCOS(profileStreamCOAssembler
                    .assembleDomainToFullModelViewCollection(userService.findAllBy_Id(source.getProfileDOList())));
            co.setNumberOfUsers(source.getProfileDOList().size());

            return co;

        }
    }
}
