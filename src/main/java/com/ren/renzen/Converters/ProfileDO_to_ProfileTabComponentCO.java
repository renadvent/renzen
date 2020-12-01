package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ProfileDTOs;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.ArticleStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.CommunityStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.ProfileStreamCOAssembler;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Repositories.CommunityRepository;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.ImageService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProfileDO_to_ProfileTabComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ProfileDO, ProfileDTOs.ProfileTabComponentCO> {

    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    final CommunityRepository communityRepository;
    final ArticleRepository articleRepository;

    final ArticleService articleService;
    final CommunityService communityService;
    final ImageService imageService;

    final ArticleStreamCOAssembler articleStreamCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final CommunityStreamCOAssembler communityStreamCOAssembler;

    public ProfileDO_to_ProfileTabComponentCO(CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityRepository communityRepository, ArticleRepository articleRepository, ArticleService articleService, CommunityService communityService, ImageService imageService, ArticleStreamCOAssembler articleStreamCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler) {
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

        co.setArticleDraftIDList(source.getArticleDraftIDList());
        co.setName(source.getUsername());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());

        var correctedImageIDs = new ArrayList<String>();
        var originalImageIDs = new ArrayList<String>();

        for (var link : source.getPublicScreenshotsIDList()) {
            String name = link.substring(link.lastIndexOf('/') + 1);
            originalImageIDs.add(name);
            correctedImageIDs.add(imageService.generateSAS(name));
        }

        co.setScreenshotLinks(correctedImageIDs);
        co.setOriginalLinks(originalImageIDs);
        co.setWorkNames(source.getWorkNames());

        return co;

    }

    @Override
    public ProfileDTOs.ProfileTabComponentCO convertDomainToPublicView(ProfileDO source) {

        final var co = common(source);

        co.setArticleDraftInfoComponentCOs(articleStreamCOAssembler
                .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleDraftIDList())));

        co.setCommunityInfoComponentCOS(communityStreamCOAssembler
                .assembleDomainToPublicModelViewCollection(communityService.findBy_idIn(source.getCommunityIDList())));
        co.setArticleInfoComponentCOS(articleStreamCOAssembler
                .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleIDList())));
        co.setArticleBookmarksCM(articleStreamCOAssembler
                .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleBookmarkIDList())));

        return co;
    }

    @Override
    public ProfileDTOs.ProfileTabComponentCO convertDomainToFullView(ProfileDO source) {

        final var co = common(source);

        co.setCommunityInfoComponentCOS(communityStreamCOAssembler
                .assembleDomainToFullModelViewCollection(communityService.findBy_idIn(source.getCommunityIDList())));
        co.setArticleInfoComponentCOS(articleStreamCOAssembler
                .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleIDList())));
        co.setArticleBookmarksCM(articleStreamCOAssembler
                .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleBookmarkIDList())));

        return co;
    }
}
