package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ArticleInfoComponentCO;
import com.ren.renzen.Converters.ArticleDO_to_ArticleStreamComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class ArticleStreamCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ArticleDO, ArticleInfoComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    public ArticleStreamCOAssembler(ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO) {
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
    }



    @Override
    public ArticleInfoComponentCO assembleDomainToPublicModelView(ArticleDO entity) {
        ArticleInfoComponentCO articleInfoComponentCO = articleDO_to_articleStreamComponentCO.convertDomainToPublicView(entity);

        //class coEXT extends RepresentationModelAssembler<ArticleDO>

        return articleInfoComponentCO;

//        return articleInfoComponentCO
//                .add(List.of(
//                        linkTo(methodOn(ArticleEditorController.class).getArticleStreamComponentCO(articleInfoComponentCO.getObjectId())).withRel("Stream_Version"),
//                        linkTo(methodOn(ArticleEditorController.class).getArticleTabComponentCO(articleInfoComponentCO.getObjectId())).withRel("Tab_Version")));
//

    }

    @Override
    public ArticleInfoComponentCO assembleDomainToFullModelView(ArticleDO entity) {
        ArticleInfoComponentCO articleInfoComponentCO = articleDO_to_articleStreamComponentCO.convertDomainToPublicView(entity);

        return articleInfoComponentCO;

//        return articleInfoComponentCO
//                .add(List.of(
//                        linkTo(methodOn(ArticleEditorController.class).getArticleStreamComponentCO(articleInfoComponentCO.getObjectId())).withRel("Stream_Version"),
//                        linkTo(methodOn(ArticleEditorController.class).getArticleTabComponentCO(articleInfoComponentCO.getObjectId())).withRel("Tab_Version")));
//

    }
}
