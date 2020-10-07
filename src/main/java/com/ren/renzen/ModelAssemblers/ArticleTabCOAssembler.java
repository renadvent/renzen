package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import com.ren.renzen.Converters.ArticleDO_to_ArticleTabComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Test Assembler for Rest
 * adds a link to the response
 * <p>
 * use in future
 * /profile
 * links:
 * /ProfileStreamCO
 * /ProfileTabCO
 * <p>
 * /ListOfArticles
 * etc....
 * <p>
 * so use a single profile id, and get a list of possible responses.
 * in this case, possible responses are different CO's
 */
@Component
public class ArticleTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ArticleDO, ArticleTabComponentCO> {

    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;

    public ArticleTabCOAssembler(ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO) {
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
    }

    @Override
    public ArticleTabComponentCO assembleDomainToPublicModelView(ArticleDO articleDO) {

            ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToPublicView(articleDO);

            return articleTabComponentCO;

//            return articleTabComponentCO
//
//                    .add(List.of(
//                            linkTo(methodOn(ArticleEditorController.class).getArticleStreamComponentCO(articleTabComponentCO.getObjectId())).withRel("Stream_Version"),
//                            linkTo(methodOn(ArticleEditorController.class).getArticleTabComponentCO(articleTabComponentCO.getObjectId())).withRel("Tab_Version")));
//
    }

    @Override
    public ArticleTabComponentCO assembleDomainToFullModelView(ArticleDO articleDO) {

            ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToFullView(articleDO);

            return articleTabComponentCO;

//            return articleTabComponentCO
//
//                    .add(List.of(
//                            linkTo(methodOn(ArticleEditorController.class).getArticleStreamComponentCO(articleTabComponentCO.getObjectId())).withRel("Stream_Version"),
//                            linkTo(methodOn(ArticleEditorController.class).getArticleTabComponentCO(articleTabComponentCO.getObjectId())).withRel("Tab_Version")));
//
    }
}

