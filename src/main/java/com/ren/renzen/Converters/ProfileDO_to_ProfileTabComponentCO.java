package com.ren.renzen.Converters;

import com.mongodb.lang.Nullable;
import com.ren.renzen.CommandObjects.ProfileTabComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.ArticleStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.CommunityStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.ProfileStreamCOAssembler;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Repositories.CommunityRepository;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.ImageService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProfileDO_to_ProfileTabComponentCO implements Converter<ProfileDO, ProfileTabComponentCO> {

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

    @Synchronized
    @Nullable
    @Override
    public ProfileTabComponentCO convert(ProfileDO source) {

        final ProfileTabComponentCO co = new ProfileTabComponentCO();

        co.setName(source.getUsername());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());
        co.setNumberOfDiscussionContentPosts(source.getDiscussionContentIDs().size());
        co.setDiscussionContentIDList(source.getDiscussionContentIDs());
        co.setCommunityStreamComponentCOList(communityStreamCOAssembler
                .toCollectionModel(communityService.findBy_idIn(source.getCommunityIDList())));
        co.setArticleHomePageCOList(articleStreamCOAssembler
                .toCollectionModel(articleService.findBy_idIn(source.getArticleIDList())));
        co.setArticleBookmarksCM(articleStreamCOAssembler
                .toCollectionModel(articleService.findBy_idIn(source.getArticleBookmarkIDList())));


        var corrected = new ArrayList<String>();

        for (var link : source.getScreenshotsIDList()){

            String name = link.substring(link.lastIndexOf('/') + 1);

            corrected.add(imageService.generateSAS(name));
        }

        co.setScreenshotLinks(corrected);

        //co.setScreenshotLinks(source.getScreenshotsIDList().stream().toArray()(imageService::generateSAS));

        return co;
    }
}
