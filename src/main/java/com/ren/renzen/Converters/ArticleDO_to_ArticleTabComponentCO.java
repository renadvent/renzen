package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ArticleDTOs;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.ArticleSectionDO;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Repositories.UserRepository;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.ImageService;
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
public class ArticleDO_to_ArticleTabComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ArticleDO, ArticleDTOs.ArticleTabComponentCO> {

    final UserRepository userRepo;
    final ArticleRepository articleRepo;
    final ArticleService articleService;
    final UserService userService;
    final CommunityService communityService;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO;

    final ImageService imageService;


    @Autowired
    public ArticleDO_to_ArticleTabComponentCO(UserRepository repo, ArticleRepository articleRepo, ArticleService articleService, UserService userService, CommunityService communityService, ProfileDO_to_ProfileStreamComponentCO profileDOtoprofileStreamComponentCO, ArticleSectionDO_to_ArticleSectionCO articleSectionDO_to_articleSectionCO, ImageService imageService) {
        this.userRepo = repo;
        this.articleRepo = articleRepo;
        this.articleService = articleService;
        this.userService = userService;
        this.communityService = communityService;
        profileDO_to_profileStreamComponentCO = profileDOtoprofileStreamComponentCO;
        this.articleSectionDO_to_articleSectionCO = articleSectionDO_to_articleSectionCO;
        this.imageService = imageService;
    }

    void common(ArticleDO source, ArticleDTOs.ArticleTabComponentCO co) {

        try {
            String name = source.getPostImageURL().substring(source.getPostImageURL().lastIndexOf('/') + 1);

            co.setImage(imageService.generateSAS(name));


        } catch (Exception e) {
            co.setImage(null);
        }


        //co.setImage(source.getImage());
        co.setComments(source.getComments());
        co.setWorkName(source.getWorkName());
        co.setCommunityID(source.getCommunityID());

        if (source.getCommunityID()!=null){
            co.setCommunityName(communityService.findBy_id(source.getCommunityID()).getName());
        }



    }

    @Synchronized
    @Nullable
    @Override
    public ArticleDTOs.ArticleTabComponentCO convertDomainToPublicView(ArticleDO source) {
        final ArticleDTOs.ArticleTabComponentCO co = new ArticleDTOs.ArticleTabComponentCO();

        common(source, co);

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setUserID(source.getCreatorID());
        userRepo.findById(source.getCreatorID()).ifPresent(user -> co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToPublicView(user)));
        co.setUserName(co.getProfileInfoComponentCO().getName());
        co.setDiscussionID(source.getDiscussionID());

        co.setPostText(source.getPostText());
        co.setPostType(source.getPostType());

        co.setLikes(source.getLikes());
        co.setDislikes(source.getDislikes());

        for (ArticleSectionDO articleSectionDO : source.getArticleSectionDOList()) {
            co.getArticleSectionCOList().add(articleSectionDO_to_articleSectionCO.convert(articleSectionDO));
        }

        return co;
    }

    @Synchronized
    @Nullable
    @Override
    public ArticleDTOs.ArticleTabComponentCO convertDomainToFullView(ArticleDO source) {
        final ArticleDTOs.ArticleTabComponentCO co = new ArticleDTOs.ArticleTabComponentCO();

        common(source, co);

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setUserID(source.getCreatorID());
        userRepo.findById(source.getCreatorID()).ifPresent(user -> co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToFullView(user)));
        co.setUserName(co.getProfileInfoComponentCO().getName());
        co.setDiscussionID(source.getDiscussionID());

        co.setLikes(source.getLikes());
        co.setDislikes(source.getDislikes());

        for (ArticleSectionDO articleSectionDO : source.getArticleSectionDOList()) {
            co.getArticleSectionCOList().add(articleSectionDO_to_articleSectionCO.convert(articleSectionDO));
        }

        return co;
    }
}
