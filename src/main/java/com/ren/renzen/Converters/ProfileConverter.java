package com.ren.renzen.Converters;

import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.ModelAssemblers.ArticleAssembler;
import com.ren.renzen.ModelAssemblers.CommunityAssembler;
import com.ren.renzen.ModelAssemblers.ProfileAssembler;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Repositories.CommunityRepository;
import com.ren.renzen.ResourceObjects.CommandObjects.ProfileDTOs;
import com.ren.renzen.ResourceObjects.DomainObjects.ProfileDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.ImageService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class ProfileConverter {
    @Component
    public static class ProfileDO_to_ProfileStreamComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ProfileDO, ProfileDTOs.ProfileInfoComponentCO> {

        final UserService userService;

        @Autowired
        public ProfileDO_to_ProfileStreamComponentCO(UserService userService) {
            this.userService = userService;
        }

        public ProfileDTOs.ProfileInfoComponentCO common(ProfileDO source) {

            final ProfileDTOs.ProfileInfoComponentCO co = new ProfileDTOs.ProfileInfoComponentCO();

            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());
            co.setName(source.getUsername());
            co.setProfilePictureLink(source.getProfilePictureLink());

            return co;

        }

        @Override
        public ProfileDTOs.ProfileInfoComponentCO convertDomainToPublicView(ProfileDO source) {

            var co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
            return co;
        }

        @Override
        public ProfileDTOs.ProfileInfoComponentCO convertDomainToFullView(ProfileDO source) {

            var co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_FULL);
            return co;
        }
    }

    @Component
    public static class ProfileDO_to_ProfileTabComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ProfileDO, ProfileDTOs.ProfileTabComponentCO> {

        final CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;
        final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

        final CommunityRepository communityRepository;
        final ArticleRepository articleRepository;

        final ArticleService articleService;
        final CommunityService communityService;
        final ImageService imageService;

        final ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler;
        final ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler;
        final CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler;

        public ProfileDO_to_ProfileTabComponentCO(CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityRepository communityRepository, ArticleRepository articleRepository, ArticleService articleService, CommunityService communityService, ImageService imageService, ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler, ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler, CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler) {
            this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
            this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
            this.communityRepository = communityRepository;
            this.articleRepository = articleRepository;
            this.articleService = articleService;
            this.communityService = communityService;
            this.imageService = imageService;
            this.articleStreamCOAssembler = articleStreamCOAssembler;
            this.profileStreamCOAssembler = profileStreamCOAssembler;
            this.communityStreamCOAssembler = communityStreamCOAssembler;
        }

        ProfileDTOs.ProfileTabComponentCO common(ProfileDO source) {

            final ProfileDTOs.ProfileTabComponentCO co = new ProfileDTOs.ProfileTabComponentCO();



            co.setName(source.getUsername());
            co.set_id(source.get_id().toHexString());
            co.setObjectId(source.get_id());

//            co.setArticleDraftIDList(source.getArticleDraftIDList());
//            co.setNumberOfDrafts(source.getArticleDraftIDList().size());

            co.setNumberOfArticles(source.getArticleIDList().size());
            co.setNumberOfCommunities(source.getJoinedCommunityIDList().size());

            co.setWorkNames(source.getWorkNames());

            return co;

        }

        @Override
        public ProfileDTOs.ProfileTabComponentCO convertDomainToPublicView(ProfileDO source) {

            final var co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

//            co.setArticleDraftInfoComponentCOS(articleStreamCOAssembler
//                    .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleDraftIDList())));

            co.setCommunityInfoComponentCOS(communityStreamCOAssembler
                    .assembleDomainToPublicModelViewCollection(communityService.findBy_idIn(source.getJoinedCommunityIDList())));
            co.setArticleInfoComponentCOS(articleStreamCOAssembler
                    .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleIDList())));
            co.setArticleBookmarksCM(articleStreamCOAssembler
                    .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleBookmarkIDList())));

            return co;
        }

        @Override
        public ProfileDTOs.ProfileTabComponentCO convertDomainToFullView(ProfileDO source) {

            final var co = common(source);
            co.setACCESS_TYPE(ACCESS_TYPE_FULL);

            //NEW
            co.setArticleDraftIDList(source.getArticleDraftIDList());
            co.setNumberOfDrafts(source.getArticleDraftIDList().size());

            co.setArticleDraftInfoComponentCOS(articleStreamCOAssembler
                    .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleDraftIDList())));

            co.setCommunityInfoComponentCOS(communityStreamCOAssembler
                    .assembleDomainToFullModelViewCollection(communityService.findBy_idIn(source.getJoinedCommunityIDList())));
            co.setArticleInfoComponentCOS(articleStreamCOAssembler
                    .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleIDList())));
            co.setArticleBookmarksCM(articleStreamCOAssembler
                    .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleBookmarkIDList())));

            return co;

        }
    }
}
