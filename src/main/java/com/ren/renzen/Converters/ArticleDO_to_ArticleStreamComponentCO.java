package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ArticleInfoComponentCO;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.springframework.stereotype.Component;

@Component
public class ArticleDO_to_ArticleStreamComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ArticleDO, ArticleInfoComponentCO> {

    final UserService userService;
    final ArticleService articleService;

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ArticleDO_to_ArticleStreamComponentCO(UserService userService, ArticleService articleService, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        this.userService = userService;
        this.articleService = articleService;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    //TODO set up to avoid code duplication in future
    public void sharedSet(ArticleDO source){

    }

    @Override
    public ArticleInfoComponentCO convertDomainToPublicView(ArticleDO source) {

        final ArticleInfoComponentCO co = new ArticleInfoComponentCO();
        co.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

        //sharedSet(source)

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());

        co.setLikes(source.getLikes());
        co.setDislikes(source.getDislikes());

        co.setAuthorID(source.getCreatorID().toHexString());
        var author = userService.findBy_id(source.getCreatorID());

        //ProfileDO author = null;
//        source.getCreatorID().ifPresent(e->{
//            co.setAuthorID(e.toHexString());
//        });;
//        var author=userService.findBy_id(co.getAuthorID());



        co.setAuthorName(author.getUsername());
        co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToPublicView(author));


        co.setTagList(source.getTagList());
        co.setPostText(source.getPostText());
        co.setPostType(source.getPostType());
        co.setComments(source.getComments());
        co.setPollOptions(source.getPollOptions());
        co.setImageIDs(source.getImageIDs());

        return co;
    }

    @Override
    public ArticleInfoComponentCO convertDomainToFullView(ArticleDO source) {
        final ArticleInfoComponentCO co = new ArticleInfoComponentCO();
        co.setACCESS_TYPE(ACCESS_TYPE_FULL);

        co.setLikes(source.getLikes());
        co.setDislikes(source.getDislikes());

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setAuthorID(source.getCreatorID().toHexString());

        var author = userService.findBy_id(source.getCreatorID());

        co.setAuthorName(author.getUsername());
        co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToFullView(author));

        co.setTagList(source.getTagList());
        co.setPostText(source.getPostText());
        co.setPostType(source.getPostType());
        co.setComments(source.getComments());
        co.setPollOptions(source.getPollOptions());
        co.setImageIDs(source.getImageIDs());


        return co;
    }
}
