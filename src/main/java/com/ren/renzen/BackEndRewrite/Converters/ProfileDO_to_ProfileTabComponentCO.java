package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ArticleDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import com.ren.renzen.BackEndRewrite.ModelAssemblers.ArticleStreamCOAssembler;
import com.ren.renzen.BackEndRewrite.ModelAssemblers.CommunityStreamCOAssembler;
import com.ren.renzen.BackEndRewrite.ModelAssemblers.ProfileStreamCOAssembler;
import com.ren.renzen.BackEndRewrite.Repositories.ArticleRepository;
import com.ren.renzen.BackEndRewrite.Repositories.CommunityRepository;
import com.mongodb.lang.Nullable;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.ArticleService;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.CommunityService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDO_to_ProfileTabComponentCO implements Converter<ProfileDO, ProfileTabComponentCO> {

    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    final CommunityRepository communityRepository;
    final ArticleRepository articleRepository;

    final ArticleService articleService;
    final CommunityService communityService;

    final ArticleStreamCOAssembler articleStreamCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final CommunityStreamCOAssembler communityStreamCOAssembler;

    public ProfileDO_to_ProfileTabComponentCO(CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityRepository communityRepository, ArticleRepository articleRepository, ArticleService articleService, CommunityService communityService, ArticleStreamCOAssembler articleStreamCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler) {
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.communityRepository = communityRepository;
        this.articleRepository = articleRepository;
        this.articleService = articleService;
        this.communityService = communityService;
        this.articleStreamCOAssembler = articleStreamCOAssembler;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.communityStreamCOAssembler = communityStreamCOAssembler;
    }

    @Synchronized
    @Nullable
    @Override
    public ProfileTabComponentCO convert(ProfileDO source){

        final ProfileTabComponentCO co = new ProfileTabComponentCO();

        co.setName(source.getUsername());

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());

        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());
        co.setNumberOfDiscussionContentPosts(source.getDiscussionContentIDs().size());

        co.setArticleIDList(source.getArticleIDList());
        co.setCommunityIDList(source.getCommunityIDList());

        co.setDiscussionContentIDList(source.getDiscussionContentIDs());

        /**
         * here id lookup is done manually instead of by converter
         * (compared to CommunityDO to Tab
         */
        //creates CommunityCOs from list of DO ids
//        Iterable<CommunityDO> communityDOList = communityRepository.findAllById(source.getCommunityIDList());
//        co.setCommunityStreamComponentCOList(communityDO_to_communityStreamComponentCO.convert(communityDOList));

        co.setCommunityStreamComponentCOList(communityStreamCOAssembler
                .toCollectionModel(communityService.findBy_idIn(source.getCommunityIDList())));


//        //create articleCOs from list of DO ids
//        Iterable<ArticleDO> articleDOList = articleRepository.findAllById(source.getArticleIDList());
//        co.setArticleHomePageCOList(articleDO_to_articleStreamComponentCO.convert(articleDOList));

        co.setArticleHomePageCOList(articleStreamCOAssembler
                .toCollectionModel(articleService.findBy_idIn(source.getArticleIDList())));

        return co;
    }
}
