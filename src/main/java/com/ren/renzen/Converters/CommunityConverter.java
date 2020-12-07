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

import java.util.stream.Collectors;

public class CommunityConverter {
    @Component

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

            co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());
            co.setName(source.getName());

            return co;
        }

        @Override
        public CommunityDTOs.CommunityInfoComponentCO convertDomainToFullView(CommunityDO source) {
            CommunityDTOs.CommunityInfoComponentCO co = new CommunityDTOs.CommunityInfoComponentCO();

            co.setACCESS_TYPE(ACCESS_TYPE_FULL);


            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());
            co.setName(source.getName());

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

        public CommunityDTOs.CommunityTabComponentCO common(CommunityDO source) {

            var co = new CommunityDTOs.CommunityTabComponentCO();

            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());

            co.setName(source.getName());

            //only gets articles that are not drafts
            co.setArticleInfoComponentCOS(
                    articleStreamCOAssembler
                    .assembleDomainToPublicModelViewCollection(
                            articleService.findBy_idIn(
                                    source.getArticleDOList()
                            ).stream().filter(article-> (!article.getIsDraft())).collect(Collectors.toList())
                    ));


            co.setProfileInfoComponentCOS(profileStreamCOAssembler
                    .assembleDomainToPublicModelViewCollection(userService.findAllBy_Id(source.getProfileDOList())));

            co.setNumberOfUsers(source.getProfileDOList().size());
            co.setNumberOfArticles(source.getArticleDOList().size());

            return co;
        }

        @Synchronized
        @Nullable
        @Override
        public CommunityDTOs.CommunityTabComponentCO convertDomainToPublicView(CommunityDO source) {

            var co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

            return co;
        }

        @Synchronized
        @Nullable
        @Override
        public CommunityDTOs.CommunityTabComponentCO convertDomainToFullView(CommunityDO source) {

            var co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_FULL);

            return co;

        }
    }
}
