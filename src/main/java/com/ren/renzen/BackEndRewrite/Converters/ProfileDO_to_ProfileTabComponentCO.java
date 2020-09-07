package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ArticleDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import com.ren.renzen.BackEndRewrite.Repositories.ArticleRepository;
import com.ren.renzen.BackEndRewrite.Repositories.CommunityRepository;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDO_to_ProfileTabComponentCO implements Converter<ProfileDO, ProfileTabComponentCO> {

    final CommunityDO_to_CommunityStreamComponentCO communityConverter;
    final ArticleDO_to_ArticleStreamComponentCO articleConverter;

    final CommunityRepository communityRepository;
    final ArticleRepository articleRepository;


    public ProfileDO_to_ProfileTabComponentCO(CommunityDO_to_CommunityStreamComponentCO communityConverter, ArticleDO_to_ArticleStreamComponentCO articleConverter, CommunityRepository communityRepository, ArticleRepository articleRepository) {
        this.communityConverter = communityConverter;
        this.articleConverter = articleConverter;
        this.communityRepository = communityRepository;
        this.articleRepository = articleRepository;
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
        Iterable<CommunityDO> communityDOList = communityRepository.findAllById(source.getCommunityIDList());
        co.setCommunityStreamComponentCOList(communityConverter.convert(communityDOList));

        //create articleCOs from list of DO ids
        Iterable<ArticleDO> articleDOList = articleRepository.findAllById(source.getArticleIDList());
        co.setArticleHomePageCOList(articleConverter.convert(articleDOList));

        return co;
    }
}