package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.ResourceObjects.CommandObjects.ArticleDTOs;
import com.ren.renzen.Controllers.ArticleEditorController;
import com.ren.renzen.Controllers.ArticleViewerController;
import com.ren.renzen.Controllers.FeedController;
import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ArticleStreamCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ArticleDO, ArticleDTOs.ArticleInfoComponentCO> {

    final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    public ArticleStreamCOAssembler(ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO) {
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
    }


    @Override
    public ArticleDTOs.ArticleInfoComponentCO assembleDomainToPublicModelView(ArticleDO entity) {
        ArticleDTOs.ArticleInfoComponentCO articleInfoComponentCO = articleDO_to_articleStreamComponentCO.convertDomainToPublicView(entity);
        return addLinksWithCurrentAuthentication(articleInfoComponentCO);
    }

    @Override
    public ArticleDTOs.ArticleInfoComponentCO assembleDomainToFullModelView(ArticleDO entity) {
        ArticleDTOs.ArticleInfoComponentCO articleInfoComponentCO = articleDO_to_articleStreamComponentCO.convertDomainToPublicView(entity);
        return addLinksWithCurrentAuthentication(articleInfoComponentCO);
    }

    @Override
    public ArticleDTOs.ArticleInfoComponentCO addLinksWithCurrentAuthentication(ArticleDTOs.ArticleInfoComponentCO entity) {

//        entity.add

        //Link findOneLink = linkTo(methodOn(controllerClass).findOne(id)).withSelfRel();

        return entity
                .add(List.of(


                        //TODO adding REST links
                        linkTo(methodOn(ArticleEditorController.class).likeArticle(entity.getObjectId(), getAuth())).withRel("LikeArticle"),
                        linkTo(methodOn(ArticleEditorController.class).dislikeArticle(entity.getObjectId(), getAuth())).withRel("DislikeArticle"),
                        linkTo(methodOn(ArticleEditorController.class).deleteArticle(entity.getObjectId(), getAuth())).withRel("DeleteArticle"),

                        linkTo(FeedController.class).slash("/addComment").withRel("addComment"),

                        //linkTo(methodOn(FeedController.class).addComment())

                        //linkTo(FeedController.class,)

                        //linkTo(methodOn(FeedController.class).addComment(entity.getObjectId(),null,getAuth())).withRel("AddComment"),

                        linkTo(methodOn(ArticleViewerController.class).getArticleStreamComponentCO(entity.getObjectId(), getAuth()))
                                .withRel("Stream_Version"),

                        //TODO working on this link
                        linkTo(methodOn(ArticleViewerController.class).getArticleStreamComponentCO(entity.getObjectId(), getAuth())).withSelfRel()
                                .andAffordance(afford(methodOn(FeedController.class).addComment(entity.getObjectId(), null, getAuth())))
//                                .withRel("Stream_Version")


                        ,
                        linkTo(methodOn(ArticleViewerController.class).getArticleTabComponentCO(entity.getObjectId(), getAuth())).withRel("Tab_Version"))


                );

    }
}
