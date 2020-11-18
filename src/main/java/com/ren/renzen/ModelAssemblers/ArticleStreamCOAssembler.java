package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ArticleInfoComponentCO;
import com.ren.renzen.Controllers.ArticleEditorController;
import com.ren.renzen.Controllers.ArticleViewerController;
import com.ren.renzen.Controllers.FeedController;
import com.ren.renzen.Converters.ArticleDO_to_ArticleStreamComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticleStreamCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ArticleDO, ArticleInfoComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    public ArticleStreamCOAssembler(ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO) {
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
    }


    @Override
    public ArticleInfoComponentCO assembleDomainToPublicModelView(ArticleDO entity) {
        ArticleInfoComponentCO articleInfoComponentCO = articleDO_to_articleStreamComponentCO.convertDomainToPublicView(entity);
        return addLinksWithCurrentAuthentication(articleInfoComponentCO);
    }

    @Override
    public ArticleInfoComponentCO assembleDomainToFullModelView(ArticleDO entity) {
        ArticleInfoComponentCO articleInfoComponentCO = articleDO_to_articleStreamComponentCO.convertDomainToPublicView(entity);
        return addLinksWithCurrentAuthentication(articleInfoComponentCO);
    }

    @Override
    public ArticleInfoComponentCO addLinksWithCurrentAuthentication(ArticleInfoComponentCO entity) {
        return entity
                .add(List.of(

                        //TODO adding REST links
                        linkTo(methodOn(ArticleEditorController.class).likeArticle(entity.getObjectId(),getAuth())).withRel("LikeArticle"),
                        linkTo(methodOn(ArticleEditorController.class).dislikeArticle(entity.getObjectId(),getAuth())).withRel("DislikeArticle"),
                        linkTo(methodOn(ArticleEditorController.class).deleteArticle(entity.getObjectId(),getAuth())).withRel("DeleteArticle"),

                        //linkTo(methodOn(FeedController.class).addComment(entity.getObjectId(),null,getAuth())).withRel("AddComment"),



                        linkTo(methodOn(ArticleViewerController.class).getArticleStreamComponentCO(entity.getObjectId(), getAuth())).withRel("Stream_Version"),
                        linkTo(methodOn(ArticleViewerController.class).getArticleTabComponentCO(entity.getObjectId(), getAuth())).withRel("Tab_Version")));

    }
}
