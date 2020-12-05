package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ArticleDTOs;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.ImageService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArticleDO_to_ArticleStreamComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ArticleDO, ArticleDTOs.ArticleInfoComponentCO> {

    final UserService userService;
    final ArticleService articleService;
    final ImageService imageService;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ArticleDO_to_ArticleStreamComponentCO(UserService userService, ArticleService articleService, ImageService imageService, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        this.userService = userService;
        this.articleService = articleService;
        this.imageService = imageService;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Override
    public ArticleDTOs.ArticleInfoComponentCO convertDomainToPublicView(ArticleDO source) {

        final ArticleDTOs.ArticleInfoComponentCO co = new ArticleDTOs.ArticleInfoComponentCO();
        co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setLikes(source.getLikes());
        co.setDislikes(source.getDislikes());
        co.setOriginalImageLink(co.getImage());
        co.setTagList(source.getTagList());
        co.setPostText(source.getPostText());
        co.setPostType(source.getPostType());
        co.setComments(source.getComments());
        co.setPollOptions(source.getPollOptions());
        co.setImageIDs(source.getImageIDs());
        co.setOriginalImageLink(co.getImage());
        co.setWorkName(source.getWorkName());
        co.setAuthorID(source.getCreatorID().toHexString());

        try {
            String name = source.getPostImageURL().substring(source.getPostImageURL().lastIndexOf('/') + 1);

            co.setImage(imageService.generateSAS(name));
        } catch (Exception e) {
            co.setImage(null);
        }

        var author = userService.findBy_id(source.getCreatorID());
        co.setAuthorName(author.getUsername());

        co.setOtherPostsInWork(
                articleService.findAllByCreatorIDAndWorkName(author.get_id(), co.getWorkName())
                        .stream().filter(articleDO -> !articleDO.getIsDraft()).map(ArticleDO::get_id).collect(Collectors.toList())
        );

        co.setOtherPostsInWorkHex(co.getOtherPostsInWork().stream().map(ObjectId::toHexString).collect(Collectors.toList()));
        co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToPublicView(author));

        return co;
    }

    @Override
    public ArticleDTOs.ArticleInfoComponentCO convertDomainToFullView(ArticleDO source) {

        //get all public info
        final var co = convertDomainToPublicView(source);

        co.setACCESS_TYPE(ACCESS_TYPE_FULL);

        //get private info

        return co;
    }

}
