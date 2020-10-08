package com.ren.renzen.Converters;

import com.mongodb.lang.Nullable;
import com.ren.renzen.CommandObjects.CommunityTabComponentCO;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.ModelAssemblers.ArticleStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.ProfileStreamCOAssembler;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class CommunityDO_to_CommunityTabComponentCO
        extends DOMAIN_VIEW_CONVERTER_SUPPORT<CommunityDO, CommunityTabComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    final ArticleService articleService;
    final UserService userService;

    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ArticleStreamCOAssembler articleStreamCOAssembler;

    public CommunityDO_to_CommunityTabComponentCO(ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, ArticleService articleService, UserService userService, ProfileStreamCOAssembler profileStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.articleService = articleService;
        this.userService = userService;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.articleStreamCOAssembler = articleStreamCOAssembler;
    }

    @Synchronized
    @Nullable
    @Override
    public CommunityTabComponentCO convertDomainToPublicView(CommunityDO source) {

        var co = new CommunityTabComponentCO();

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
    public CommunityTabComponentCO convertDomainToFullView(CommunityDO source) {

        var co = new CommunityTabComponentCO();
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
