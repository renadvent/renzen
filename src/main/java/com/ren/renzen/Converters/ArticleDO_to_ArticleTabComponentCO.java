package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.ArticleSectionDO;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Repositories.UserRepository;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Converts ArticleDO to ArticleComponentCO
 * which is a format that allows the React application to render the Article
 */

@Component
public class ArticleDO_to_ArticleTabComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ArticleDO, ArticleTabComponentCO> {

    final UserRepository userRepo;
    final ArticleRepository articleRepo;
    final UserService userService;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO;

    @Autowired
    public ArticleDO_to_ArticleTabComponentCO(UserRepository repo, ArticleRepository articleRepo, UserService userService, ProfileDO_to_ProfileStreamComponentCO profileDOtoprofileStreamComponentCO, ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO) {
        this.userRepo = repo;
        this.articleRepo = articleRepo;
        this.userService = userService;
        profileDO_to_profileStreamComponentCO = profileDOtoprofileStreamComponentCO;
        this.articleSectionDO_to_articleSectionCO = articleSectionDO_to_articleSectionCO;
    }

    @Synchronized
    @Nullable
    @Override
    public ArticleTabComponentCO convertDomainToPublicView(ArticleDO source) {
        final ArticleTabComponentCO co = new ArticleTabComponentCO();

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setUserID(source.getCreatorID());
        userRepo.findById(source.getCreatorID()).ifPresent(user -> co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToPublicView(user)));
        co.setUserName(co.getProfileInfoComponentCO().getName());
        co.setDiscussionID(source.getDiscussionID());

        for (ArticleSectionDO articleSectionDO : source.getArticleSectionDOList()) {
            co.getArticleSectionCOList().add(articleSectionDO_to_articleSectionCO.convert(articleSectionDO));
        }

        return co;
    }

    @Synchronized
    @Nullable
    @Override
    public ArticleTabComponentCO convertDomainToFullView(ArticleDO source) {
        final ArticleTabComponentCO co = new ArticleTabComponentCO();

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setUserID(source.getCreatorID());
        userRepo.findById(source.getCreatorID()).ifPresent(user -> co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToFullView(user)));
        co.setUserName(co.getProfileInfoComponentCO().getName());
        co.setDiscussionID(source.getDiscussionID());

        for (ArticleSectionDO articleSectionDO : source.getArticleSectionDOList()) {
            co.getArticleSectionCOList().add(articleSectionDO_to_articleSectionCO.convert(articleSectionDO));
        }

        return co;
    }
}
