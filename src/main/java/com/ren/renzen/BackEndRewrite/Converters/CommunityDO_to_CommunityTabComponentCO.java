package com.ren.renzen.BackEndRewrite.Converters;

import com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ArticleDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import com.ren.renzen.BackEndRewrite.ModelAssemblers.ArticleStreamCOAssembler;
import com.ren.renzen.BackEndRewrite.ModelAssemblers.ProfileStreamCOAssembler;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.ArticleService;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.UserService;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommunityDO_to_CommunityTabComponentCO implements Converter<CommunityDO, CommunityTabComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final DiscussionDO_to_DiscussionComponentCO discussionDO_to_discussionComponentCO;


    final ArticleService articleService;
    final UserService userService;

    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ArticleStreamCOAssembler articleStreamCOAssembler;

    public CommunityDO_to_CommunityTabComponentCO(ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, DiscussionDO_to_DiscussionComponentCO discussionDO_to_discussionComponentCO, ArticleService articleService, UserService userService, ProfileStreamCOAssembler profileStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.discussionDO_to_discussionComponentCO = discussionDO_to_discussionComponentCO;
        this.articleService = articleService;
        this.userService = userService;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.articleStreamCOAssembler = articleStreamCOAssembler;
    }

    /**
     * Should Discussion section be sent seperately???? not right now
     *
     * @param source
     * @return
     */
    @Synchronized
    @Nullable
    @Override
    public CommunityTabComponentCO convert(CommunityDO source) {

        CommunityTabComponentCO co = new CommunityTabComponentCO();

        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setName(source.getName());


        var allBy_id = articleService.findBy_idIn(source.getArticleDOList());

        co.setArticle_Article_streamComponentCOList(articleStreamCOAssembler
                        .toCollectionModel(articleService.findBy_idIn(source.getArticleDOList())));

//        co.setArticle_Article_streamComponentCOList(articleDO_to_articleStreamComponentCO.convert(source.getArticleDOList()));
        co.setNumberOfArticles(source.getArticleDOList().size());


        co.setUser_streamComponentCOList(profileStreamCOAssembler
                .toCollectionModel(userService.findAllBy_Id(source.getProfileDOList())));

        //co.setUser_streamComponentCOList(profileDO_to_profileStreamComponentCO.convert(source.getProfileDOList()));
        co.setNumberOfUsers(source.getProfileDOList().size());

        /**
         * here id lookup is done by converter
         */
        co.setDiscussionDiscussionComponentCO(discussionDO_to_discussionComponentCO.convert(source.getDiscussionID()));

        return co;
    }
}
