package com.ren.renzen.Converters;

import com.ren.renzen.CommandObjects.ArticleInfoComponentCO;
import com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER_SUPPORT;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.ImageService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArticleDO_to_ArticleStreamComponentCO extends DOMAIN_VIEW_CONVERTER_SUPPORT<ArticleDO, ArticleInfoComponentCO> {

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

    //TODO set up to avoid code duplication in future
    public void common(ArticleDO source){


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

        co.setOriginalImageLink(co.getImage());



        try {
            String name = source.getImage().substring(source.getImage().lastIndexOf('/') + 1);

            co.setImage(imageService.generateSAS(name));
        } catch (Exception e) {
            co.setImage(null);
        }

        co.setAuthorID(source.getCreatorID().toHexString());
        var author = userService.findBy_id(source.getCreatorID());

        co.setWorkName(source.getWorkName());



//        System.out.println(author.getArticleIDList());
//        var otherWorks = author.getArticleIDList().stream()
//                .filter(articleID->{
//                    var article = articleService.findBy_id(articleID);
//                    return article.getWorkName().equals(co.getWorkName());
//                }).collect(Collectors.toList());
//
//        //get ids of other posts in work to scroll through
//        co.setOtherPostsInWork(otherWorks);
//
//        System.out.println("other works");
//        System.out.println(otherWorks);

        co.setOtherPostsInWork(
                articleService.findAllByCreatorIDAndWorkName(author.get_id(),co.getWorkName())
                .stream().map(ArticleDO::get_id).collect(Collectors.toList())
        );

        co.setOtherPostsInWorkHex(co.getOtherPostsInWork().stream().map(ObjectId::toHexString).collect(Collectors.toList()));





        //ProfileDO author = null;
//        source.getCreatorID().ifPresent(e->{
//            co.setAuthorID(e.toHexString());
//        });;
//        var author=userService.findBy_id(co.getAuthorID());



        co.setAuthorName(author.getUsername());
        co.setProfileInfoComponentCO(profileDO_to_profileStreamComponentCO.convertDomainToPublicView(author));


        co.setOriginalImageLink(co.getImage());


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




        try {
            String name = source.getImage().substring(source.getImage().lastIndexOf('/') + 1);

            co.setImage(imageService.generateSAS(name));
        } catch (Exception e) {
            co.setImage(null);
        }


        //co.setImage(source.getImage());





        co.setLikes(source.getLikes());
        co.setDislikes(source.getDislikes());

        co.setName(source.getArticleName());
        co.setDescription(source.getDescription());
        co.set_id(source.get_id().toHexString());
        co.setObjectId(source.get_id());
        co.setAuthorID(source.getCreatorID().toHexString());

        var author = userService.findBy_id(source.getCreatorID());


        co.setWorkName(source.getWorkName());


//        var otherWorks = author.getArticleIDList().stream()
//                .filter(articleID->{
//                    var article = articleService.findBy_id(articleID);
//                    return article.getWorkName().equals(co.getWorkName());
//                }).collect(Collectors.toList());
//
//        //get ids of other posts in work to scroll through
//        co.setOtherPostsInWork(otherWorks);

        co.setOtherPostsInWork(
                articleService.findAllByCreatorIDAndWorkName(author.get_id(),co.getWorkName())
                        .stream().map(ArticleDO::get_id).collect(Collectors.toList())
        );

        co.setOtherPostsInWorkHex(co.getOtherPostsInWork().stream().map(ObjectId::toHexString).collect(Collectors.toList()));


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
