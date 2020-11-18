package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ProfileTabComponentCO;
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
public class ProfileDO_to_ProfileTabComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ProfileDO, ProfileTabComponentCO> {

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

    void common(ProfileDO source, ProfileTabComponentCO co){



    }
    @Override
    public ProfileTabComponentCO convertDomainToPublicView(ProfileDO source) {
        final ProfileTabComponentCO co = new ProfileTabComponentCO();

        common(source,co);

        co.setName(source.getUsername());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());
        co.setCommunityInfoComponentCOS(communityStreamCOAssembler
                .assembleDomainToPublicModelViewCollection(communityService.findBy_idIn(source.getCommunityIDList())));
        co.setArticleInfoComponentCOS(articleStreamCOAssembler
                .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleIDList())));


        co.setArticleBookmarksCM(articleStreamCOAssembler
                .assembleDomainToPublicModelViewCollection(articleService.findBy_idIn(source.getArticleBookmarkIDList())));




        var corrected = new ArrayList<String>();
        var orig = new ArrayList<String>();

        for (var link : source.getPublicScreenshotsIDList()) {

            String name = link.substring(link.lastIndexOf('/') + 1);

            orig.add(name);
            corrected.add(imageService.generateSAS(name));


        }

        co.setScreenshotLinks(corrected);
        co.setOriginalLinks(orig);

        //co.setScreenshotLinks(source.getScreenshotsIDList().stream().toArray()(imageService::generateSAS));

        return co;
    }

    @Override
    public ProfileTabComponentCO convertDomainToFullView(ProfileDO source) {

        final ProfileTabComponentCO co = new ProfileTabComponentCO();

        common(source,co);


        co.setName(source.getUsername());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());

        co.setCommunityInfoComponentCOS(communityStreamCOAssembler
                .assembleDomainToFullModelViewCollection(communityService.findBy_idIn(source.getCommunityIDList())));
        co.setArticleInfoComponentCOS(articleStreamCOAssembler
                .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleIDList())));
        co.setArticleBookmarksCM(articleStreamCOAssembler
                .assembleDomainToFullModelViewCollection(articleService.findBy_idIn(source.getArticleBookmarkIDList())));


        var corrected = new ArrayList<String>();
        var orig = new ArrayList<String>();



        for (var link : source.getPublicScreenshotsIDList()) {

            String name = link.substring(link.lastIndexOf('/') + 1);
            orig.add(name);

            corrected.add(imageService.generateSAS(name));
        }

        co.setScreenshotLinks(corrected);
        co.setOriginalLinks(orig);


        //co.setScreenshotLinks(source.getScreenshotsIDList().stream().toArray()(imageService::generateSAS));

        return co;
    }
}
